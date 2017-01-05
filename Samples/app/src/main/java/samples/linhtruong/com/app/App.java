package samples.linhtruong.com.app;

import android.app.Application;

import samples.linhtruong.com.app.uireactive.component.RxComponent;
import samples.linhtruong.com.app.uireactive.module.RxModule;
import samples.linhtruong.com.base.BaseApplication;

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
        RxComponent rxComponent = RxComponent.Initializer.init(new RxModule(this), new AppModule(this));
        setComponent(rxComponent);

        rxComponent.inject(rxComponent.getTaskResource());
        rxComponent.inject(rxComponent.getTaskManager());
    }
}
