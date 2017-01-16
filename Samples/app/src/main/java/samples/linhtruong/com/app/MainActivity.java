package samples.linhtruong.com.app;

import android.app.Activity;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.view.View;

import org.androidannotations.annotations.EActivity;

import javax.inject.Inject;

import samples.linhtruong.com.app.facebook.FacebookShareActivity_;
import samples.linhtruong.com.app.freetest.SOTestActivity_;
import samples.linhtruong.com.app.freetest.rxtest.TestRxActivity_;
import samples.linhtruong.com.app.freetest.rxtest.TestRxComponentsActivity_;
import samples.linhtruong.com.app.freetest.rxtest.TestRxDownloadFileActivity_;
import samples.linhtruong.com.app.lrucache.LruCacheTestActivity_;
import samples.linhtruong.com.app.tabs.TabHostActivity;
import samples.linhtruong.com.app.tabs.TabHostActivity_;
import samples.linhtruong.com.app.tabs.TabPagerActivity_;
import samples.linhtruong.com.app.uireactive.RxActivity_;
import samples.linhtruong.com.app.uireactive.data.DataObserver;
import samples.linhtruong.com.app.uireactive.task.BaseTask;
import samples.linhtruong.com.app.uireactive.task.TaskExecutable;
import samples.linhtruong.com.app.uireactive.task.TaskManager;
import samples.linhtruong.com.base.BaseActivity;
import samples.linhtruong.com.base.BaseApplication;

@EActivity
public class MainActivity extends BaseActivity implements TaskExecutable {

    @Inject
    TaskManager taskManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        injectToAppComponent();
    }

    @Override
    public <T> void executeTask(BaseTask<T> task, DataObserver<? super T> callBack) {
        if (callBack == null) {
            taskManager.executeTaskAndTrigger(task);
        } else {
            taskManager.executeTask(task);
        }
    }

    private void injectToAppComponent() {
        ((BaseApplication) getApplication()).getBaseComponent().inject(this);
    }

    private void initLayout(final Activity activity) {

        SOTestActivity_.intent(this).start();
        TestRxDownloadFileActivity_.intent(this).start();
        TestRxComponentsActivity_.intent(this).start();
        TabHostActivity_.intent(this).start();
        TabPagerActivity_.intent(this).start();
        FacebookShareActivity_.intent(this).start();
        findViewById(R.id.rxActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxActivity_.intent(activity).start();
            }
        });

        findViewById(R.id.rxTestActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestRxActivity_.intent(activity).start();
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
    }
}
