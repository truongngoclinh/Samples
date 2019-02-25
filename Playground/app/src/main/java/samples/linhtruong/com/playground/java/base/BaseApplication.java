package samples.linhtruong.com.playground.java.base;

import android.app.Application;
import timber.log.Timber;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 8/21/18 - 16:31.
 * @organization VED
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
    }
}
