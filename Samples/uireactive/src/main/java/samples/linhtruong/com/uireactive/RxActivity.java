package samples.linhtruong.com.uireactive;

import android.os.Bundle;
import android.support.annotation.Nullable;

import org.androidannotations.annotations.EActivity;

import javax.inject.Inject;

import samples.linhtruong.com.base.BaseActivity;
import samples.linhtruong.com.base.BaseApplication;
import samples.linhtruong.com.uireactive.data.DataObserver;
import samples.linhtruong.com.uireactive.task.BaseTask;
import samples.linhtruong.com.uireactive.task.TaskExecutable;
import samples.linhtruong.com.uireactive.task.TaskManager;

/**
 * Created by Truong on 9/26/16 - 18:22.
 * Description:
 */

@EActivity
public class RxActivity extends BaseActivity implements TaskExecutable {

    @Inject
    TaskManager taskManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        injectIntoAppComponent();
    }

    private void injectToAppComponent() {
        ((BaseApplication) getApplication()).mBaseComponent.inject(this);
    }

    @Override
    public <T> void executeTask(BaseTask<T> task, DataObserver<? super T> callBack) {
        taskManager.executeTask(task);
    }
}
