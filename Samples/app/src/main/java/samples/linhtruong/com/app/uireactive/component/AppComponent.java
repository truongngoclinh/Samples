package samples.linhtruong.com.app.uireactive.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import samples.linhtruong.com.app.uireactive.module.AppModule;
import samples.linhtruong.com.app.uireactive.task.TaskManager;
import samples.linhtruong.com.base.BaseComponent;
import samples.linhtruong.com.base.task.TaskResource;

/**
 * Created by Truong on 9/26/16 - 17:28.
 * Description:
 */

@Singleton
@Component (
        modules = {AppModule.class}
)
public interface AppComponent extends BaseComponent {

    final class Initializer {
        public static AppComponent init(AppModule module) {
            return DaggerAppComponent.builder().appModule(module).build();
        }
    }

    void inject(TaskManager taskManager);
    void inject(TaskResource taskResource);

    Context getContext();

    TaskResource getTaskResource();
    TaskManager getTaskManager();
}
