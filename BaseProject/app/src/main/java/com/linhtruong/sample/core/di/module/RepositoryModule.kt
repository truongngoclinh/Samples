package news.agoda.com.sample.di.module

import dagger.Module
import dagger.Provides
import com.linhtruong.sample.explore.repo.MainRepository
import com.linhtruong.sample.explore.repo.MainRepositoryImpl
import retrofit2.Retrofit
import javax.inject.Singleton


/**
 * @author linhtruong
 */
@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideMainRepository(retrofit: Retrofit): MainRepository = MainRepositoryImpl(retrofit)
}