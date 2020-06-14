package samples.linhtruong.com.app.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTabHost;

import org.androidannotations.annotations.EActivity;

import samples.linhtruong.com.app.R;
import samples.linhtruong.com.app.tabs.utils.MyFragmentTabHost;
import samples.linhtruong.com.app.tabs.utils.PhotosFragment;
import samples.linhtruong.com.app.tabs.utils.SongFragment;
import samples.linhtruong.com.app.tabs.utils.VideosFragment;

/**
 * Created by linhtruong on 12/2/16 - 10:46.
 * Description: this activity represents usage of tabhost
 */

@EActivity
public class TabHostActivity extends FragmentActivity {

    private FragmentTabHost mTabHost;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tabhost);

        initTab();
    }

    private void initTab() {
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        TabHost.TabSpec songSpec = mTabHost.newTabSpec("Song").setIndicator(getIndicator("Song", R.drawable.selector_songs));
        TabHost.TabSpec photoSpec = mTabHost.newTabSpec("Photo").setIndicator(getIndicator("Photo", R.drawable.selector_photos));
        TabHost.TabSpec videoSpec = mTabHost.newTabSpec("Video").setIndicator(getIndicator("Video", R.drawable.selector_videos));

        mTabHost.addTab(songSpec, SongFragment.class, null);
        mTabHost.addTab(photoSpec, PhotosFragment.class, null);
        mTabHost.addTab(videoSpec, VideosFragment.class, null);
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
