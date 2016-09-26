package samples.linhtruong.com.app.uireactive;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import javax.inject.Inject;

import samples.linhtruong.com.app.R;
import samples.linhtruong.com.app.uireactive.data.DataObserver;
import samples.linhtruong.com.app.uireactive.task.BaseTask;
import samples.linhtruong.com.app.uireactive.task.TaskExecutable;
import samples.linhtruong.com.app.uireactive.task.TaskManager;
import samples.linhtruong.com.base.BaseActivity;
import samples.linhtruong.com.base.BaseApplication;

/**
 * Created by Truong on 9/26/16 - 18:22.
 * Description:
 */

@EActivity
public class RxActivity extends BaseActivity implements TaskExecutable {

    @Inject
    TaskManager taskManager;

    @ViewById(R.id.buttonAdd)
    Button mBtnAdd;

    @ViewById(R.id.buttonUpdate)
    Button mBtnUpdate;

    @ViewById(R.id.numberOfRecords)
    TextView mNumberOfRecords;

    @ViewById(R.id.firstRecordName)
    TextView mFirstRecordName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);

        injectToAppComponent();
    }

    private void injectToAppComponent() {
        ((BaseApplication) getApplication()).mBaseComponent.inject(this);
    }

    public <T> void executeTask(BaseTask<T> task, DataObserver<? super T> callBack) {
        if (callBack == null) {
            taskManager.executeTaskAndTrigger(task);
        } else {
            taskManager.executeTask(task);
        }
    }
}
