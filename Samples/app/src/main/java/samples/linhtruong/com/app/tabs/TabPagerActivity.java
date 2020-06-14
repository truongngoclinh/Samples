package samples.linhtruong.com.app.tabs;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.android.material.tabs.TabLayout;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import samples.linhtruong.com.app.R;
import samples.linhtruong.com.app.tabs.utils.MyPager;
import samples.linhtruong.com.app.tabs.utils.PhotosFragment;
import samples.linhtruong.com.app.tabs.utils.SongFragment;
import samples.linhtruong.com.app.tabs.utils.VideosFragment;

/**
 * Created by linhtruong on 12/2/16 - 10:53.
 * Description: this activity represents usage of tab with viewpager
 */

@EActivity
public class TabPagerActivity extends FragmentActivity {

    private static final String TAG = "TabPagerActivity";

    @ViewById(R.id.tabLayout)
    TabLayout mTabLayout;

    @ViewById(R.id.pager)
    MyPager mPager;

    private PagerAdapter mAdapter;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tab_viewpager);

        setupPager();
        setupTab();
    }

    private void setupPager() {
        mAdapter = new PagerAdapter(getSupportFragmentManager());
        mAdapter.addFragment(new SongFragment(), "Song");
        mAdapter.addFragment(new PhotosFragment(), "Photo");
        mAdapter.addFragment(new VideosFragment(), "Video");

        mPager.setAdapter(mAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setupTab() {
        mTabLayout.setupWithViewPager(mPager);

        mTabLayout.getTabAt(0).setCustomView(getIndicator("Song", R.drawable.selector_songs));
        mTabLayout.getTabAt(1).setCustomView(getIndicator("Photo", R.drawable.selector_photos));
        mTabLayout.getTabAt(2).setCustomView(getIndicator("Video", R.drawable.selector_videos));
      /*  mTabLayout.getTabAt(0).setIcon(R.drawable.selector_songs);
        mTabLayout.getTabAt(1).setIcon(R.drawable.selector_photos);
        mTabLayout.getTabAt(2).setIcon(R.drawable.selector_videos);*/
    }

    private class PagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mFragments = new ArrayList<>();
        private List<String> mTitles = new ArrayList<>();

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mTitles.add(title);
        }

        public void reset() {
            mFragments.clear();
            mTitles.clear();
        }

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }
    }

    private View getIndicator(String title, int res) {
        View v = LayoutInflater.from(this).inflate(R.layout.activity_tabhost_indicator, null);
        ImageView img = (ImageView) v.findViewById(R.id.image);
        TextView txt = (TextView) v.findViewById(R.id.txtView);

        img.setImageResource(res);
        txt.setText(title);

        return v;
    }
}
