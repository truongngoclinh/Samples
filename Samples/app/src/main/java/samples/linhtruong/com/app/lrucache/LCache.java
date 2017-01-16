package samples.linhtruong.com.app.lrucache;

import android.util.LruCache;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 1/16/17 - 23:12.
 * @organization VED
 */

public class LCache<K, V> {

    private final int size = 4 * 1024 * 1024;

    private static volatile LCache mCache;
    private LruCache<K, V> mLruCache;

    public static LCache getCache() {
        synchronized (mCache) {
            if (mCache == null) {
                synchronized (mCache) {
                    mCache = new LCache();
                }
            }
        }

        return mCache;
    }

    private LCache() {
        mLruCache = new LruCache<>(size);
    }

    public LruCache<K, V> getLruCache() {
        return mLruCache;
    }
}
