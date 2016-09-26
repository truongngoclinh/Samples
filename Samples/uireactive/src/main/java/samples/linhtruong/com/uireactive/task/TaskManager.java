package samples.linhtruong.com.uireactive.task;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func0;
import samples.linhtruong.com.base.task.TaskResource;

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
}
