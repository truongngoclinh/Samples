package samples.linhtruong.com.base;

import android.app.Application;

/**
 * Created by Truong on 9/24/16 - 00:10.
 * Description:
 */

public class BaseApplication extends Application {

    public BaseComponent mBaseComponent;

    public void setComponent(BaseComponent component) {
        mBaseComponent = component;
    }
}
