package samples.linhtruong.com.app;

import android.os.Bundle;

import android.support.annotation.Nullable;

import org.androidannotations.annotations.EActivity;

import javax.inject.Inject;

import samples.linhtruong.com.app.facebook.FacebookShareActivity_;
import samples.linhtruong.com.app.tabs.TabHostActivity;
import samples.linhtruong.com.app.tabs.TabHostActivity_;
import samples.linhtruong.com.app.tabs.TabPagerActivity_;
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

//        RxActivity_.intent(this).start();
//        RxTestActivity_.intent(this).start();
//        SOTestActivity_.intent(this).start();
//        TestRxDownloadFileActivity_.intent(this).start();
//        TestRxComponentsActivity_.intent(this).start();
//        TabHostActivity_.intent(this).start();
//        TabPagerActivity_.intent(this).start();
        FacebookShareActivity_.intent(this).start();
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
}
