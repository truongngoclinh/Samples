package samples.linhtruong.com.app.lrucache;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 1/16/17 - 23:12.
 * @organization VED
 */

public class LCache {

    private static final String TAG = "LCache";

    private static LCache mInstance;
    private static LruCache mLruCache;

    public static LCache getInstance() {
        if (mInstance == null) {
            synchronized (LCache.class) {
                mInstance = new LCache();
            }
        }

        return mInstance;
    }

    private LCache() {
    }

    public void initCache(int size) {
        Log.d(TAG, "initCache: " + size);
        if (mLruCache == null) {
            mLruCache = new LruCache<String, Bitmap>(size) {

                protected int sizeOf(String key, Bitmap value) {
                    Log.d(TAG, "sizeOf: getByteCount = " + value);
                    return value.getByteCount() / 1024; // as kb
                }
            };
        }
    }

    public LruCache getLruCache() {
        cacheState();
        return mLruCache;
    }

    public void cacheState() {
        Log.d(TAG, "******** cacheState: ********"
                + "\n maxSize = " + mLruCache.maxSize()
                + "\n ?size = " + mLruCache.size()
                + "\n putCount = " + mLruCache.putCount()
                + "\n hitCount = " + mLruCache.hitCount()
                + "\n missCount = " + mLruCache.missCount()
                + "\n evictCount = " + mLruCache.evictionCount()
                + "\n createCount = " + mLruCache.createCount()
        );
    }

}
