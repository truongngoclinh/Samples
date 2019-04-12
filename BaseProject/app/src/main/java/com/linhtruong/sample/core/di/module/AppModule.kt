package news.agoda.com.sample.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import com.linhtruong.sample.core.platform.App
import javax.inject.Singleton


/**
 * @author linhtruong
 */
@Module
class AppModule(private val application: App) {
    @Provides
    @Singleton
    fun provideApplicationContext(): Application = application
}