package samples.linhtruong.com.app.memoryleak;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;

import samples.linhtruong.com.app.R;
import samples.linhtruong.com.base.BaseActivity;

/**
 * Anonymous inner classes: i.e callback
 *
 * @author linhtruong
 * @date 2/20/17 - 14:28.
 * @organization VED
 */

public class LeakCase2 extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doTaskLeak();
        doTask();
    }

    /**
     * This is memory leak.
     * Since {@link LSingleton} is singleton, it will in the memory for the lifetime of the application.
     * The {@link LCallback} object is passed to this singleton and stored in mCallbackQueue variable.
     * So there is a reference to {@link LCallback} object from a static object in memory.
     * So the parent class of {@link LCallback} object wont be cleared if needed.
     */
    private void doTaskLeak() {
        LSingleton.getInstance().doTask(new LCallback() {
            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted() {

            }
        });
    }

    /**
     * Avoid passing anonymous inner class object to static/singleton classes
     * which keep strong reference to it.
     * The solution is make it {@link java.lang.ref.WeakReference}
     */
    private void doTask() {
        LSingleton.getInstance().doTask(new LCallback() {
            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted() {

            }
        });
    }
}

