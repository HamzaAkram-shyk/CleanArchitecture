package com.example.cleanarchitecture.base

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.cleanarchitecture.BuildConfig
import com.example.cleanarchitecture.presentation.extentions.asSingleEvent
import com.example.cleanarchitecture.util.*
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import javax.inject.Inject


@AndroidEntryPoint
open class BaseActivity() : AppCompatActivity() {
    @Inject
    protected lateinit var networkConnection: NetworkConnection
    private lateinit var callback: NetworkCallback
    fun setConnectionCallback(callback: NetworkCallback) {
        this.callback = callback
    }
    private lateinit var imageCallback: (uri: Uri?) -> Unit
    private var imageUri: Uri? = null
    private val getImageFromGallery =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let { data ->
                imageCallback(data)
            }
        }

    private val getCameraImageResult =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
            if (isSuccess) {
                imageCallback(imageUri!!)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        monitorInternetConnection()
    }


    private fun monitorInternetConnection() {
        networkConnection.asSingleEvent()
        networkConnection.observe(this, Observer {
            if (it) {
                callback.onInternetConnect()
            } else {
                callback.onInternetDisconnect()
            }
        })
    }

    private fun getTmpFileUri(): Uri {
        val tmpFile = File.createTempFile("tmp_image_file", ".png", cacheDir).apply {
            createNewFile()
            deleteOnExit()
        }

        return FileProvider.getUriForFile(
            applicationContext,
            "${BuildConfig.APPLICATION_ID}.provider",
            tmpFile
        )
    }

    protected fun openGallery(imageCallback: (uri: Uri?) -> Unit) {
        if (this.imageCallback == null) {
            this.imageCallback = imageCallback
        }
        getImageFromGallery.launch("image/*")
    }

    protected fun openCamera(imageCallback: (uri: Uri?) -> Unit) {
        if (this.imageCallback == null) {
            this.imageCallback = imageCallback
        }
        getTmpFileUri().let { uri ->
            imageUri = uri
            getCameraImageResult.launch(uri)
        }
    }


}


interface NetworkCallback {
    fun onInternetDisconnect()
    fun onInternetConnect()
}

