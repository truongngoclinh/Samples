package samples.linhtruong.com.base.manager;

import java.util.ArrayList;
import java.util.List;

import samples.linhtruong.com.base.BaseSingleton;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 2/6/17 - 14:21.
 * @organization VED
 */

public class SingletonManager {

    private final static List<BaseSingleton> mList = new ArrayList<>();

    public static void register(BaseSingleton singleton) {
        synchronized (SingletonManager.class) {
           mList.add(singleton);
        }
    }

    public static void unregisterAll() {
        synchronized (SingletonManager.class) {
            for (BaseSingleton singleton : mList) {
                singleton.onDestroy();
            }

            mList.clear();
        }
    }
}
