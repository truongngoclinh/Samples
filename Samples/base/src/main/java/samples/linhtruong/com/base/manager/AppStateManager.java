package samples.linhtruong.com.base.manager;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 2/7/17 - 10:01.
 * @organization VED
 */

public class AppStateManager {

    private static volatile AppStateManager mInstance;

    public AppStateManager getInstance() {
        if (mInstance == null) {
            synchronized (AppStateManager.class) {
                if (mInstance == null) {
                    mInstance = new AppStateManager();
                }
            }
        }

        return mInstance;
    }

    private AppStateManager() {}

}
