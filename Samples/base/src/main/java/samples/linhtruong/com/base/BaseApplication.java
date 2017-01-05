package samples.linhtruong.com.base;

import android.app.Application;

import samples.linhtruong.com.utils.ScreenUtils;

/**
 * Created by Truong on 9/24/16 - 00:10.
 * Description:
 */

public class BaseApplication extends Application {

    public BaseComponent mBaseComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initHelper();
    }

    private void initHelper() {
       ScreenUtils.init(getApplicationContext());
    }

    public void setComponent(BaseComponent component) {
        mBaseComponent = component;
    }

    public BaseComponent getBaseComponent() {
        return mBaseComponent;
    }
}
