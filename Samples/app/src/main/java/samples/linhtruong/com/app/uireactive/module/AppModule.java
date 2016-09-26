package samples.linhtruong.com.app.uireactive.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import samples.linhtruong.com.app.uireactive.task.TaskManager;
import samples.linhtruong.com.base.manager.DbManager;
import samples.linhtruong.com.base.manager.ResManager;
import samples.linhtruong.com.base.task.TaskResource;

/**
 * Created by Truong on 9/26/16 - 17:28.
 * Description:
 */

@Module
public class AppModule {

    private final Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    DbManager provideDbManager() {
        return new DbManager(mApplication);
    }

    @Provides
    @Singleton
    ResManager provideResManager() {
        return new ResManager(mApplication);
    }

    @Provides
    @Singleton
    TaskResource provideTaskResource() {
        return new TaskResource();
    }

    @Provides
    @Singleton
    TaskManager provideTaskManager() {
        return new TaskManager();
    }
}
