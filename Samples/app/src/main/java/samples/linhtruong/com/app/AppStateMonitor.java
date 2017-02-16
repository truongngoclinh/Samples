package samples.linhtruong.com.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import rx.Observable;
import rx.subjects.BehaviorSubject;
import samples.linhtruong.com.utils.LogUtils;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 2/7/17 - 10:01.
 * @organization VED
 */

public class AppStateMonitor implements Application.ActivityLifecycleCallbacks {

    private final BehaviorSubject<Boolean> mForegroundStatus = BehaviorSubject.create(false);

    private int mForegroundCount = 0;

    private static volatile AppStateMonitor mInstance;

    public AppStateMonitor getInstance() {
        if (mInstance == null) {
            synchronized (AppStateMonitor.class) {
                if (mInstance == null) {
                    mInstance = new AppStateMonitor();
                }
            }
        }

        return mInstance;
    }

    private AppStateMonitor() {
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        LogUtils.d(activity.getClass().getSimpleName() + " onActivityCreated");
    }

    @Override
    public void onActivityStarted(Activity activity) {
        mForegroundCount++;
        LogUtils.d(activity.getClass().getSimpleName() + " onActivityStarted (foreground count = " + mForegroundCount + ")");

        if (mForegroundCount == 1) {
            onAppEnterForeground(activity);
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        mForegroundCount--;
        LogUtils.d(activity.getClass().getSimpleName() + "onActivityStopped (foreground count = " + mForegroundCount + ")");

        if (mForegroundCount == 0) {
            onAppEnterBackground(activity);
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        LogUtils.d(activity.getClass().getSimpleName() + " onActivitySaveInstanceState");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        LogUtils.d(activity.getClass().getSimpleName() + " onActivityDestroyed");
    }

    public Observable<Boolean> getForegroundStatus() {
        return mForegroundStatus.distinctUntilChanged();
    }

    private void onAppEnterForeground(Activity activity) {
        LogUtils.d("onAppenterForeground");
        mForegroundStatus.onNext(true);
        // analytic record here
    }

    private void onAppEnterBackground(Activity activity) {
        LogUtils.d("onAppEnterBackground");
        mForegroundStatus.onNext(false);
        // analytic record here
    }
}
