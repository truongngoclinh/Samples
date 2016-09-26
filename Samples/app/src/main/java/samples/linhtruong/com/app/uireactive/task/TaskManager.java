package samples.linhtruong.com.app.uireactive.task;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Func0;
import samples.linhtruong.com.app.uireactive.data.DataObserver;
import samples.linhtruong.com.base.task.TaskResource;
import samples.linhtruong.com.utils.LogUtils;

/**
 * Created by Truong on 9/26/16 - 18:39.
 * Description:
 */

public class TaskManager {

    @Inject
    TaskResource taskResource;

    public <T> Observable<T> executeTask(final BaseTask<T> task) {
        return Observable.defer(new Func0<Observable<T>>() {
            @Override
            public Observable<T> call() {
                return task.execute(taskResource);
            }
        });
    }

    public <T> Subscription executeTaskAndTrigger(final BaseTask<T> task) {
        return Observable.defer(new Func0<Observable<T>>() {
            @Override
            public Observable<T> call() {
                return task.execute(taskResource).doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        LogUtils.d("execute and trigger task " + task.getClass().getName());
                    }
                });
            }
        }).subscribeOn(TaskScheduler.COMPUTE).subscribe(new DataObserver<T>() {
            @Override
            public void onNext(T t) {
                //
            }
        });
    }
}
