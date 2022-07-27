package com.dvtest.weatherapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.dvtest.weatherapp.MainApp

object NetworkUtil {
    fun isNetworkAvailable(): Boolean {
        val cm =
            MainApp.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo: NetworkInfo?
        activeNetworkInfo = cm.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
    }
}