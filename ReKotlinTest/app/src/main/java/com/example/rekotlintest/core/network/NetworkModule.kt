package com.example.rekotlintest.core.network

import com.example.rekotlintest.App
import com.example.rekotlintest.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.factory
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.io.File
import java.util.concurrent.TimeUnit

const val NETWORK_MODULE = "NETWORK_MODULE"
const val RETROFIT = "RETROFIT"

val networkModule = Kodein.Module(NETWORK_MODULE) {
    bind<Retrofit>(RETROFIT) with singleton {
        Retrofit.Builder()
            .baseUrl(NetworkConst.URL)
            .client(instance())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    bind<OkHttpClient>() with factory { app: App ->
        val builder = OkHttpClient.Builder()
        try {
            var cache = Cache(
                File(
                    app.cacheDir,
                    NetworkConst.CACHE_DIR_NAME
                ), NetworkConst.CACHE_DIR_SIZE
            )
            builder.cache(cache)
        } catch (e: Exception) {
            Timber.e(Throwable("Init cache failed!"))
        }

        val loggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        builder.addNetworkInterceptor(loggingInterceptor)

        builder.connectTimeout(NetworkConst.CONNECT, TimeUnit.MILLISECONDS)
        builder.readTimeout(NetworkConst.READ, TimeUnit.MILLISECONDS)
        builder.writeTimeout(NetworkConst.WRITE, TimeUnit.MILLISECONDS)

        builder.build()
    }
}