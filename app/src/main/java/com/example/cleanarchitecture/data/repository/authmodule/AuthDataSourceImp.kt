package com.example.cleanarchitecture.data.repository.authmodule

import android.content.SharedPreferences
import com.example.cleanarchitecture.util.Resource
import javax.inject.Inject

class AuthDataSourceImp @Inject constructor(private val sharedPreferences: SharedPreferences) :
    AuthDataSource {
    private var editor = sharedPreferences.edit()
    override suspend fun saveUserToken(token: String) {
        editor.putString("userToken", token)
        editor.commit()
    }

    override suspend fun saveUserLoginStatus(isLogin: Boolean) {
        editor.putBoolean("loginStatus", isLogin)
        editor.commit()
    }

    override suspend fun getLoginStatus(): Boolean {
        return sharedPreferences.getBoolean("loginStatus", false)
    }

    override suspend fun getUserToken(): String? {
        return sharedPreferences.getString("userToken", "null")
    }

}