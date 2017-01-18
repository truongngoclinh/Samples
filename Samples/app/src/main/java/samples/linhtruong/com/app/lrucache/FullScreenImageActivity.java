package samples.linhtruong.com.app.lrucache;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import samples.linhtruong.com.app.R;
import samples.linhtruong.com.base.BaseActivity;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 1/17/17 - 18:12.
 * @organization VED
 */

@EActivity
public class FullScreenImageActivity  extends BaseActivity {

    private static final String TAG = "FullScreenImageActivity";
    public static final String EXTRA_URL = "extra_url";

    @ViewById(R.id.img)
    ImageView mImgView;

    public static void navigate(Context context, String url) {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lru_test_full_screen_img);

        String url = getIntent().getStringExtra(EXTRA_URL);
        Bitmap bitmap = (Bitmap) LCache.getInstance().getLruCache().get(url);
        if (bitmap != null) {
            Log.d(TAG, "onCreate: load bitmap successfully");
            mImgView.setImageBitmap(bitmap);
        } else {

            Log.d(TAG, "onCreate: load bitmap failed!");
        }
    }
}
