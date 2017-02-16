package samples.linhtruong.com.base.database.task;

import io.realm.Realm;
import samples.linhtruong.com.utils.LogUtils;

/**
 * Created by Truong on 9/27/16 - 10:39.
 * Description: using onPostCommit to notify change
 */

public abstract class UpdateTask<T> implements DbTask<T> {

    @Override
    public T onExecute(Realm db) {
        T result = null;

        LogUtils.d("Start update transaction");
        db.beginTransaction();
        LogUtils.d("Enter transaction");
        try {
            result = execute(db);
            db.commitTransaction();
            LogUtils.d("Commit transaction");
        } catch (Exception e) {
            db.cancelTransaction();
            LogUtils.d("Cancel transaction");
            e.printStackTrace();
        }

        return result;
    }

    protected abstract T execute(Realm db);

    protected abstract void onPostCommit();

}
