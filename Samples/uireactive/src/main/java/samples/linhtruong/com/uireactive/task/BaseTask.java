package samples.linhtruong.com.uireactive.task;

import rx.Observable;
import samples.linhtruong.com.base.task.TaskResource;

/**
 * Created by Truong on 9/26/16 - 18:33.
 * Description:
 */

public abstract class BaseTask<T> {

    abstract Observable<T> execute(TaskResource taskResource);
}
