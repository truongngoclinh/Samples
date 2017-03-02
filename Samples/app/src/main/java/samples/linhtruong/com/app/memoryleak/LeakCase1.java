package samples.linhtruong.com.app.memoryleak;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

import samples.linhtruong.com.app.R;
import samples.linhtruong.com.base.BaseActivity;

/**
 * Local variable, context parameter case
 *
 * @author linhtruong
 * @date 2/20/17 - 14:01.
 * @organization VED
 */

public class LeakCase1 extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new MyAsyncTaskLeak().execute();
        new MyAsyncTaskFix().execute();
        new MyAsyncTaskFixRefinement(this).execute();
    }

    /**
     * This one is memory leak.
     * Because the {@link MyAsyncTaskLeak} instance cant be garbage collected when needed,
     * it keeps strong reference to {@link LeakCase1} parent class
     */
    private class MyAsyncTaskLeak extends AsyncTask<String, Void, Void> {

        protected long startTime;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            startTime = System.currentTimeMillis();
        }

        @Override
        protected Void doInBackground(String... params) {
            doBackgroundWork();

            return null;
        }

        protected void doBackgroundWork() {
            long current = System.currentTimeMillis();

            do {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } while (current - startTime < 1 * 60 * 1000); // 1 minute
        }
    }

    /**
     * The solution is make it static nested class or in a different class file altogether.
     * This ensures that they do not keep reference to {@link LeakCase1} parent class.
     * But if we still need reference to the {@link LeakCase1} activity to update some content views?
     */
    private static class MyAsyncTaskFix extends AsyncTask<String, Void, Void> {

        protected long startTime;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            startTime = System.currentTimeMillis();
        }

        @Override
        protected Void doInBackground(String... params) {
            doBackgroundWork();

            return null;
        }

        protected void doBackgroundWork() {
            long current = System.currentTimeMillis();

            do {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } while (current - startTime < 1 * 60 * 1000); // 1 minute
        }
    }

    /**
     * We declare the reference is {@link WeakReference} so it is automatically cleared
     * by the system once the {@link LeakCase1} or the related view are destroyed.
     * */
    private static class MyAsyncTaskFixRefinement extends AsyncTask<String, Void, Void> {

        protected long startTime;
        protected WeakReference<LeakCase1> activityRef;

        public MyAsyncTaskFixRefinement(LeakCase1 activity) {
           activityRef = new WeakReference<>(activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            startTime = System.currentTimeMillis();
        }

        @Override
        protected Void doInBackground(String... params) {
            doBackgroundWork();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            LeakCase1 activity = activityRef.get();
            if (activity != null) {
                // update views
            } else {
                // forget it
            }
        }

        protected void doBackgroundWork() {
            long current = System.currentTimeMillis();

            do {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } while (current - startTime < 1 * 60 * 1000); // 1 minute
        }

    }
}
