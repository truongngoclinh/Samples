package samples.linhtruong.com.app;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 1/4/17 - 14:40.
 * @organization VED
 */

@Module
public class AppModule {

    private final Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesContext() {
        return mApplication;
    }
}
