package samples.linhtruong.com.app.uireactive.manager;

import android.content.Context;

import io.realm.Realm;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import samples.linhtruong.com.app.uireactive.database.RxDatabase;
import samples.linhtruong.com.app.uireactive.database.UserDatabase;
import samples.linhtruong.com.app.uireactive.task.RxUpdateTask;
import samples.linhtruong.com.app.uireactive.task.TaskScheduler;
import samples.linhtruong.com.base.database.Database;
import samples.linhtruong.com.base.database.task.DbTask;
import samples.linhtruong.com.base.database.task.QueryTask;

/**
 * Created by Truong on 9/27/16 - 14:31.
 * Description:
 * UI reactive concept represents here:
 * + observe db transaction then notify to all subsriber about changes
 * + the subscriber is the db task, normally this task is reponsiblity for getting data then update UI.
 * We device db transaction into 2 types:
 * + update task (add, delete also): return nothing but will fire notifies to UI relevant task
 * + query task (query for data, not update anything):  normally return realm object
 */

public class RxDbManager {

    private Context mContext;
    private RxDatabase mRxDatabase;

    public RxDbManager(Context context) {
        mContext = context;

        initDb();
    }

    private void initDb() {
        mRxDatabase = new RxDatabase(initUserDb(mContext));
    }

    private Database initUserDb(Context context) {
        return new UserDatabase(context);
    }

    // update task
    <T> Observable<T> update(RxUpdateTask<T> updateTask) {
        updateTask.setNotifySubject(mRxDatabase.getNotifySubject());
        return executask(mRxDatabase.getDatabase(), updateTask, TaskScheduler.IO);
    }

    // query task
    <T> Observable<T> query(QueryTask<T> queryTask) {
        return executask(mRxDatabase.getDatabase(), queryTask, TaskScheduler.DBRead);
    }

    <T> Observable<T> executask(final Database database, final DbTask<T> dbTask, Scheduler scheduler) {
        if (database == null) {
            return Observable.error(new IllegalStateException("database is null"));
        }

        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                Realm db = database.getInstance();

                boolean closeRealm = true;
                if (dbTask instanceof QueryTask) {
                    closeRealm = false;

                }

                T result = dbTask.onExecute(db);
                subscriber.onNext(result);
                subscriber.onCompleted();

                if (closeRealm) {
                    db.close();
                }
            }
        }).subscribeOn(scheduler);
    }

}
