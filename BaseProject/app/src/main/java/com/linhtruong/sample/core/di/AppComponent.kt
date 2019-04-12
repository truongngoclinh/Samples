package news.agoda.com.sample.di.module.di

import android.app.Application
import com.google.gson.Gson
import dagger.Component
import com.linhtruong.sample.core.platform.App
import com.linhtruong.sample.core.di.viewmodel.ViewModelModule
import news.agoda.com.sample.di.module.AppModule
import news.agoda.com.sample.di.module.NetworkModule
import news.agoda.com.sample.di.module.RepositoryModule
import com.linhtruong.sample.explore.NewsDetailFragment
import com.linhtruong.sample.explore.NewsListFragment
import javax.inject.Singleton


/**
 * @author linhtruong
 */
@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, RepositoryModule::class, ViewModelModule::class])
interface AppComponent {
    companion object Initializer {
        fun init(app: App) : AppComponent {
            return DaggerAppComponent.builder().appModule(AppModule(app)).build()
        }
    }

    fun application(): Application
    fun gson(): Gson

    fun inject(app: App)
    fun inject(fragment: NewsListFragment)
    fun inject(fragment: NewsDetailFragment)
}