package samples.linhtruong.com.app.uireactive.task;

import rx.Observable;
import samples.linhtruong.com.base.task.TaskResource;

/**
 * Created by Truong on 9/26/16 - 18:33.
 * Description:
 */

public abstract class BaseTask<T> {

    protected abstract Observable<T> execute(TaskResource taskResource);
}
