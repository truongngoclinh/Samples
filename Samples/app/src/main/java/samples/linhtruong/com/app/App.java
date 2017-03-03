package samples.linhtruong.com.app;

import samples.linhtruong.com.base.BaseApplication;
import samples.linhtruong.com.base.loop.UILooper;
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

    }

    private void initUtils() {
        ScreenUtils.init(this);
    }

    private void initSingleton() {
        UILooper.getInstance().init();
    }
}
