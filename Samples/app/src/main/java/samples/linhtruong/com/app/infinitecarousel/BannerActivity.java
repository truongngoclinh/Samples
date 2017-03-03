package samples.linhtruong.com.app.infinitecarousel;

import android.widget.LinearLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import samples.linhtruong.com.app.R;
import samples.linhtruong.com.base.BaseActivity;

/**
 * Provide an auto scroll banners with interval {@link BannerView#ROTATE_INTERVAL_MILLS}
 *
 * @author linhtruong
 * @date 3/2/17 - 16:02.
 * @organization VED
 */

@EActivity(R.layout.activity_simple_infinite_carousel)
public class BannerActivity extends BaseActivity {

    @ViewById(R.id.root)
    LinearLayout mRoot;

    @AfterViews
    protected void afterViews() {
        BannerView bannerView = new BannerView(this);
        mRoot.addView(bannerView);

        List<Banner> data = new ArrayList<>();
        data.add(Banner.create("https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcQnEA3urcvKiBFj0IfuGaKvXVM-Y63vCL3FeJKeVokWPXhhUHFU", 0));
        data.add(Banner.create("https://lh5.googleusercontent.com/-_Q45vX28D9o/UuSjfEQgtUI/AAAAAAAAHNw/PyUkFaqmxEk/s640/DSC_0049.JPG", 1));
        data.add(Banner.create("http://www.walltor.com/images/wallpaper/flowers-on-glass-10770.jpg", 2));

        bannerView.setData(data, null);
    }

}
