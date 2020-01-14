package samples.linhtruong.com.app.drawer;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import samples.linhtruong.com.app.R;
import samples.linhtruong.com.base.BaseActivity;
import samples.linhtruong.com.base.BaseFragment;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 2/7/17 - 16:10.
 * @organization VED
 */

@EActivity
public class DrawerActivity extends BaseActivity {

    private static final String urlNavHeaderBg = "http://api.androidhive.info/images/nav-menu-header-bg.jpg";
    private static final String urlProfileImg = "https://lh3.googleusercontent.com/-M8Lb7zQykwI/AAAAAAAAAAI/AAAAAAAANwI/KgUVVosJB3E/s120-p-rw-no/photo.jpg";
    private static final String TAG_HOME = "home";
    private static final String TAG_PHOTO = "photo";
    private static final String TAG_NOTIFICATION = "notification";

    private int mNavIndex;
    private String mNavTag;
    private String[] mNavTitles;
    private View mNavHeader;
    private Handler mHandler;

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    @ViewById(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @ViewById(R.id.navigation_view)
    NavigationView mNavigationView;

    private TextView mNavHeaderName;
    private TextView mNavHeaderWebsite;
    private ImageView mNavHeaderImg;
    private ImageView mNavHeaderProfile;

    @ViewById(R.id.fab)
    FloatingActionButton mFab;

    @Click(R.id.fab)
    void OnFabClick() {
        Snackbar.make(mFab, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_drawer_test);
    }

    @AfterViews
    void afterView() {
        setSupportActionBar(mToolbar);
        mNavTitles = getResources().getStringArray(R.array.nav_item_activity_titles);
        mNavHeader = mNavigationView.getHeaderView(0);
        mNavHeaderName = (TextView) mNavHeader.findViewById(R.id.name);
        mNavHeaderWebsite = (TextView) mNavHeader.findViewById(R.id.website);
        mNavHeaderImg = (ImageView) mNavHeader.findViewById(R.id.img_header_bg);
        mNavHeaderProfile = (ImageView) mNavHeader.findViewById(R.id.img_profile);
        mHandler = new Handler();

        loadNavHeader();
        initNavigationView();
    }

    private void loadNavHeader() {
        mNavHeaderName.setText("Linh Truong");
        mNavHeaderWebsite.setText("www.linhtruong.me");

        Glide.with(this).load(urlNavHeaderBg).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mNavHeaderImg);
        Glide.with(this).load(urlProfileImg).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .bitmapTransform(new CircleTransform(this))
                .into(mNavHeaderProfile);

        mNavigationView.getMenu().getItem(2).setActionView(R.layout.menu_dot);
    }

    private void initNavigationView() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        mNavIndex = 0;
                        mNavTag = TAG_HOME;
                        break;

                    case R.id.photo:
                        mNavIndex = 1;
                        mNavTag = TAG_PHOTO;
                        break;

                    case R.id.notification:
                        mNavIndex = 2;
                        mNavTag = TAG_NOTIFICATION;
                        break;

                    case R.id.privacy:
                        // start intent
                        mDrawerLayout.closeDrawers();
                        return true;

                    case R.id.about:
                        // start intent
                        mDrawerLayout.closeDrawers();
                        return true;

                    default:
                        mNavIndex = 0;
                }

                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setCheckable(true);
                }
                item.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();

    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
            return;
        }

        if (mNavIndex != 0) {
            mNavIndex = 0;
            mNavTag = TAG_HOME;
            loadHomeFragment();
            return;
        }


        super.onBackPressed();
    }

    BaseFragment getHomeFragment() {
        switch (mNavIndex) {
            case 0:
                return new HomeFragment();

            case 1:
                return new PhotoFragment();

            case 2:
                return new NotificationFragment();

            default:
                return new HomeFragment();
        }

    }

    private void loadHomeFragment() {
        selectNavMenu();
        setToolbarTitle();

        if (getSupportFragmentManager().findFragmentByTag(mNavTag) != null) {
            mDrawerLayout.closeDrawers();

            toggleFab();
            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable pendingRunnable = new Runnable() {
            @Override
            public void run() {
                BaseFragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, mNavTag);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        if (pendingRunnable != null) {
            mHandler.post(pendingRunnable);
        }

        toggleFab();
        mDrawerLayout.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(mNavTitles[mNavIndex]);
    }

    private void selectNavMenu() {
        mNavigationView.getMenu().getItem(mNavIndex).setChecked(true);
    }

    private void toggleFab() {
        if (mNavIndex == 0) {
            mFab.show();
        } else {
            mFab.hide();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mNavIndex == 0) {
            getMenuInflater().inflate(R.menu.main, menu);
        }

        if (mNavIndex == 2) {
            getMenuInflater().inflate(R.menu.notifications, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_logout:
                Toast.makeText(getApplicationContext(), "Logout!", Toast.LENGTH_LONG).show();
                return true;

            case R.id.action_clear_notifications:
                Toast.makeText(getApplicationContext(), "Clear all notifications!", Toast.LENGTH_LONG).show();
                break;

            case R.id.action_mark_all_read:
                Toast.makeText(getApplicationContext(), "Mark all read!", Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
