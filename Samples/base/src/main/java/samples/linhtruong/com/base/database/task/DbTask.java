package samples.linhtruong.com.base.database.task;

import io.realm.Realm;

/**
 * Created by Truong on 9/27/16 - 10:23.
 * Description:
 */

public interface DbTask<T> {
   T onExecute(Realm db);
}
