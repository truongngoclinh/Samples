package samples.linhtruong.com.app.uireactive.task;

import java.util.LinkedHashMap;
import java.util.Map;

import rx.subjects.SerializedSubject;
import samples.linhtruong.com.app.uireactive.data.UpdateNotify;
import samples.linhtruong.com.base.database.task.UpdateTask;
import samples.linhtruong.com.utils.LogUtils;

/**
 * Created by Truong on 9/27/16 - 10:56.
 * Description: creating subject pool to connect between subscriber and update observable
 * then notify the change after db transaction commited from update observable to subsriber
 */

public abstract class RxUpdateTask<T> extends UpdateTask<T> {

    private SerializedSubject<UpdateNotify, UpdateNotify> mNotifySubject;
    private LinkedHashMap<Class<? extends UpdateNotify>, UpdateNotify> mNotifies;

    public void setNotifySubject(SerializedSubject<UpdateNotify, UpdateNotify> subject) {
        mNotifySubject = subject;
    }

    @Override
    protected void onPostCommit() {
        // fire notifies here
        if (mNotifies != null || mNotifySubject != null) {
            for (Map.Entry<Class<? extends UpdateNotify>, UpdateNotify> entry : mNotifies.entrySet()) {
                UpdateNotify notify = entry.getValue();
                mNotifySubject.onNext(notify);
                LogUtils.d("sending notify: " + notify.getClass().getName());
            }
        }
    }

    public void addNotify(UpdateNotify notify) {
        if (notify == null) {
            return;
        }

        if (mNotifies == null) {
            mNotifies = new LinkedHashMap<>();
        }

        UpdateNotify existing = mNotifies.get(notify.getClass());
        if (existing == null) {
            mNotifies.put(notify.getClass(), notify);
            LogUtils.d("add notify %s, ", notify.getClass());
        } else {
            // same class, need to merge
            LogUtils.d("duplicate %s, ", notify.getClass());
        }
    }

    public void removeNotify(UpdateNotify notify) {
        if (notify == null || mNotifies == null) {
            return;
        }

        mNotifies.remove(notify.getClass());
    }
}
