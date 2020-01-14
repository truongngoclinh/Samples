package samples.linhtruong.com.app.lrucache;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import samples.linhtruong.com.app.R;
import samples.linhtruong.com.base.BaseActivity;
import samples.linhtruong.com.base.listener.ItemInteractorListener;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 1/16/17 - 23:41.
 * @organization VED
 */

@EActivity
public class LruCacheTestActivity extends BaseActivity {

    private static final String TAG = "LruCacheTestActivity";

    @ViewById(R.id.list)
    RecyclerView mList;

    private ImageAdapter mAdapter;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_lru_test);

        initCache();
        initList();
        initData();
    }

    private void initList() {
        mAdapter = new ImageAdapter(new ItemInteractorListener<ImageInfo>() {
            @Override
            public void onItemClick(ImageInfo data) {
                FullScreenImageActivity_.intent(mContext).extra(FullScreenImageActivity.EXTRA_URL, data.url).start();
            }
        });

        mList.setLayoutManager(new LinearLayoutManager(this));
        mList.setAdapter(mAdapter);
    }

    private void initData() {
        List<ImageInfo> data = new ArrayList<>();
        data.add(ImageInfo.create("http://hdwallpapershdpics.com/wp-content/uploads/2016/10/romantic-gifts-for-her-love-wallpaper.jpg"));
        data.add(ImageInfo.create("http://www.love.quotesms.com/images/love-wallpaper/Best-Love-Wallpaper-for-Desktop.jpg"));
        data.add(ImageInfo.create("https://images4.alphacoders.com/342/34254.png"));
        data.add(ImageInfo.create("https://images5.alphacoders.com/296/thumb-1920-296165.jpg"));
        data.add(ImageInfo.create("http://cdn.wonderfulengineering.com/wp-content/uploads/2016/01/love-wallpaper-610x343.jpg"));
        data.add(ImageInfo.create("http://7606-presscdn-0-74.pagely.netdna-cdn.com/wp-content/uploads/2016/03/Dubai-Photos-Images-Dubai-Fountain-Image-800x600.jpg"));
        data.add(ImageInfo.create("https://www.smashingmagazine.com/wp-content/uploads/2016/01/07-responsive-image-example-castle-7-opt.jpg"));
        data.add(ImageInfo.create("http://demo.qodeinteractive.com/passage/wp-content/uploads/2013/07/revolution-05-center.jpg"));
        data.add(ImageInfo.create("http://7-themes.com/data_images/out/14/6817014-image.jpg"));

        mAdapter.setData(data);
    }

    private void initCache() {
        /*ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        int maxKb = activityManager.getMemoryClass() * 1024;*/
        int maxKb = (int) (Runtime.getRuntime().maxMemory() / 1024); // in kb
        int cacheSize = maxKb / 2; // 1/2 total ram
        Log.d(TAG, "initCache: (kb) " + cacheSize);
        LCache.getInstance().initCache(cacheSize);
    }
}
