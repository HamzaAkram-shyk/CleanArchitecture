package com.example.cleanarchitecture.util

import android.content.Context
import android.net.*
import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.InetSocketAddress
import java.net.Socket
import javax.inject.Inject

sealed class NetworkStatus {
    object Available : NetworkStatus()
    object Unavailable : NetworkStatus()
}

class NetworkStatusHelper @Inject constructor(private val connectivityManager: ConnectivityManager) :
    LiveData<NetworkStatus>() {


    private lateinit var connectivityManagerCallback: ConnectivityManager.NetworkCallback

    private fun getConnectivityManagerCallback() =
        object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                val networkCapability = connectivityManager.getNetworkCapabilities(network)
                val hasNetworkConnection =
                    networkCapability?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                        ?: false
                if (hasNetworkConnection) {
                    determineInternetAccess()
                }
                Log.e("network", "onAvailable")
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                postValue(NetworkStatus.Unavailable)
                Log.e("network", "onLost")
            }

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities,
            ) {
                super.onCapabilitiesChanged(network, networkCapabilities)
                if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                    determineInternetAccess()
                } else {
                    postValue(NetworkStatus.Unavailable)
                }
                Log.e("network", "onCapabilitiesChanged")
            }

            override fun onUnavailable() {
                super.onUnavailable()
                postValue(NetworkStatus.Unavailable)
                Log.e("network", "onUnavailable")
            }

        }

    private fun determineInternetAccess() {
        CoroutineScope(Dispatchers.IO).launch {
            if (InernetAvailablity.check()) {
                postValue(NetworkStatus.Available)
            }
        }
    }


    override fun onActive() {
        super.onActive()
        connectivityManagerCallback = getConnectivityManagerCallback()
        val networkRequest = NetworkRequest
            .Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        connectivityManager.registerNetworkCallback(networkRequest, connectivityManagerCallback)
    }

    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(connectivityManagerCallback)
    }

}

object InernetAvailablity {

    fun check(): Boolean {
        return try {
            val socket = Socket()
            socket.connect(InetSocketAddress("8.8.8.8", 53))
            socket.close()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

}