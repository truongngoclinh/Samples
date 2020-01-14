package samples.linhtruong.com.app;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;

import androidx.annotation.Nullable;

import org.androidannotations.annotations.EActivity;

import samples.linhtruong.com.app.drawer.DrawerActivity_;
import samples.linhtruong.com.app.eventbus.TestNotificationAtivity_;
import samples.linhtruong.com.app.facebook.FacebookShareActivity_;
import samples.linhtruong.com.app.fcm.activity.FirebaseTestActivity_;
import samples.linhtruong.com.app.freetest.SOTestActivity_;
import samples.linhtruong.com.app.freetest.rxtest.TestRxActivity_;
import samples.linhtruong.com.app.freetest.rxtest.TestRxComponentsActivity_;
import samples.linhtruong.com.app.freetest.rxtest.TestRxDownloadFileActivity_;
import samples.linhtruong.com.app.infinitecarousel.BannerActivity_;
import samples.linhtruong.com.app.lrucache.LruCacheTestActivity_;
import samples.linhtruong.com.app.multithreading.threadcommunication.AndroidMessagePassing_;
import samples.linhtruong.com.app.tabs.TabHostActivity_;
import samples.linhtruong.com.app.tabs.TabPagerActivity_;
import samples.linhtruong.com.app.regex.TestRegexActivity_;
import samples.linhtruong.com.base.BaseActivity;
import samples.linhtruong.com.base.manager.SingletonManager;

@EActivity
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout(this);
    }

    private void initLayout(final Activity activity) {
        findViewById(R.id.rxTestActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestRxActivity_.intent(activity).start();
            }
        });

        findViewById(R.id.soActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SOTestActivity_.intent(activity).start();
            }
        });

        findViewById(R.id.testRxDownloadFileActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestRxDownloadFileActivity_.intent(activity).start();
            }
        });

        findViewById(R.id.testRxComponentActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestRxComponentsActivity_.intent(activity).start();
            }
        });

        findViewById(R.id.tabHostActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TabHostActivity_.intent(activity).start();
            }
        });

        findViewById(R.id.tabPagerActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TabPagerActivity_.intent(activity).start();
            }
        });

        findViewById(R.id.facebookTestActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FacebookShareActivity_.intent(activity).start();
            }
        });

        findViewById(R.id.lruCacheDemo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LruCacheTestActivity_.intent(activity).start();
            }
        });

        findViewById(R.id.eventBusDemo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestNotificationAtivity_.intent(activity).start();
            }
        });

        findViewById(R.id.regexDemo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestRegexActivity_.intent(activity).start();
            }
        });

        findViewById(R.id.firebaseTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseTestActivity_.intent(activity).start();
            }
        });

        findViewById(R.id.drawer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerActivity_.intent(activity).start();
            }
        });

        findViewById(R.id.carousel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BannerActivity_.intent(activity).start();
            }
        });

        findViewById(R.id.msgTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AndroidMessagePassing_.intent(activity).start();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        SingletonManager.unregisterAll();
    }
}
