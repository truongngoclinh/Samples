package samples.linhtruong.com.app;

import samples.linhtruong.com.app.eventbus.EventBus;
import samples.linhtruong.com.app.uireactive.component.RxComponent;
import samples.linhtruong.com.app.uireactive.module.RxModule;
import samples.linhtruong.com.base.BaseApplication;
import samples.linhtruong.com.base.loop.MainUILoop;
import samples.linhtruong.com.utils.ScreenUtils;

/**
 * Created by Truong on 9/26/16 - 17:41.
 * Description:
 */

public class App extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        initDependencies();
        initUtils();
        initSingleton();
    }

    private void initDependencies() {
        RxComponent rxComponent = RxComponent.Initializer.init(new RxModule(this), new AppModule(this));
        setComponent(rxComponent);

        rxComponent.inject(rxComponent.getTaskResource());
        rxComponent.inject(rxComponent.getTaskManager());
    }

    private void initUtils() {
        ScreenUtils.init(this);
    }

    private void initSingleton() {
        MainUILoop.getInstance().init();
    }
}
