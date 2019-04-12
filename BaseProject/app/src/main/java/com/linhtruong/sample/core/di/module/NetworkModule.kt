package news.agoda.com.sample.di.module

import android.app.Application
import com.google.gson.Gson
import com.linhtruong.sample.BuildConfig
import dagger.Module
import dagger.Provides
import com.linhtruong.sample.core.network.GsonFactory
import com.linhtruong.sample.core.network.NetworkConst
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.io.File
import java.lang.Exception
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * @author linhtruong
 */
@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(NetworkConst.URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonFactory.create()

    @Provides
    @Singleton
    fun provideOkhttp3Client(application: Application): OkHttpClient {
        val builder = OkHttpClient.Builder()

        try {
            var cache = Cache(File(application.cacheDir, NetworkConst.CACHE_DIR_NAME), NetworkConst.CACHE_DIR_SIZE)
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

        return builder.build()
    }
}