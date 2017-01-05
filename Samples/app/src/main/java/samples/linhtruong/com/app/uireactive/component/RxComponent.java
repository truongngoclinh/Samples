package samples.linhtruong.com.app.uireactive.component;


import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import samples.linhtruong.com.app.AppModule;
import samples.linhtruong.com.app.uireactive.module.RxModule;
import samples.linhtruong.com.app.uireactive.task.TaskManager;
import samples.linhtruong.com.app.uireactive.task.TaskResource;
import samples.linhtruong.com.base.BaseComponent;

/**
 * Created by Truong on 9/26/16 - 17:28.
 * Description:
 */

@Singleton
@Component (
        modules = {RxModule.class, AppModule.class}
)
public interface RxComponent extends BaseComponent {

    final class Initializer {
        public static RxComponent init(RxModule rxModule, AppModule appModule) {
            return DaggerRxComponent.builder().rxModule(rxModule).appModule(appModule).build();
        }
    }

    void inject(TaskManager taskManager);
    void inject(TaskResource taskResource);

    TaskResource getTaskResource();
    TaskManager getTaskManager();
    Application getContext();

}
