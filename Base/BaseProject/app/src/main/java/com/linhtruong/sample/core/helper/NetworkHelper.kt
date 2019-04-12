package com.linhtruong.sample.core.helper

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import javax.inject.Inject
import javax.inject.Singleton


/**
 * @author linhtruong
 */
@Singleton
class NetworkHelper
@Inject constructor(private val context: Application) {
    fun isConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    return (hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || (hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)))
                }
            }
        } else {
            cm?.run {
                return cm.activeNetworkInfo != null
            }
        }

        return false
    }
}