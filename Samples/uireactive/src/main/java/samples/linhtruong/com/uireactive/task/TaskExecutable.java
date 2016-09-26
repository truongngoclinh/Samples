package samples.linhtruong.com.uireactive.task;

import samples.linhtruong.com.uireactive.data.DataObserver;

/**
 * Created by Truong on 9/26/16 - 18:30.
 * Description:
 */

public interface TaskExecutable {

    <T> void executeTask(BaseTask<T> task, DataObserver<? super T> callBack);
}
