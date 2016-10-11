package samples.linhtruong.com.app;

import samples.linhtruong.com.base.BaseApplication;
import samples.linhtruong.com.app.uireactive.component.AppComponent;
import samples.linhtruong.com.app.uireactive.module.AppModule;

/**
 * Created by Truong on 9/26/16 - 17:41.
 * Description:
 */

public class App extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        initDependencies();
    }

    private void initDependencies() {
        AppComponent component = AppComponent.Initializer.init(new AppModule(this));
        setComponent(component);

        component.inject(component.getTaskResource());
        component.inject(component.getTaskManager());
    }
}
