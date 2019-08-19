package com.example.rekotlintest

import android.app.Application
import android.content.Context
import com.example.rekotlintest.AppReducer.Companion.appReducer
import com.example.rekotlintest.core.RootRoutable
import com.example.rekotlintest.core.createThunkMiddleware
import com.example.rekotlintest.core.loggingMiddleware
import com.example.rekotlintest.core.network.NetworkConst
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.*
import org.rekotlin.DispatchFunction
import org.rekotlin.Store
import org.rekotlinrouter.Router
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.io.File
import java.util.concurrent.TimeUnit

var appStore = Store(
    state = null,
    reducer = ::appReducer,
    middleware = listOf(::loggingMiddleware, createThunkMiddleware())
)

class App : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        bind<Retrofit>() with singleton {
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
            Retrofit.Builder()
                .baseUrl(NetworkConst.URL)
                .client(instance())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        }

        bind<OkHttpClient>() with provider {
            val builder = OkHttpClient.Builder()
            try {
                var cache = Cache(
                    File(
                        this@App.cacheDir,
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
        bind<AppService>() with singleton {
            instance<Retrofit>().create(AppService::class.java)
        }

        bind<Moshi>() with singleton {
            Moshi.Builder().build()
        }
        bind<App>() with singleton { this@App }
        bind<Store<AppState>>() with singleton { appStore }
        bind<DispatchFunction>() with singleton { appStore.dispatchFunction }
        bind<CoroutineScope>() with singleton { MainScope() }
        bind<CoroutineDispatcher>() with singleton { Dispatchers.IO }
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

//        router = Router(store = appStore,
//            rootRoutable = RootRoutable(context = applicationContext),
//            stateTransform = { subscription ->
//                subscription.select { stateType ->
//                    stateType.navigationState
//                }
//            })
    }
}