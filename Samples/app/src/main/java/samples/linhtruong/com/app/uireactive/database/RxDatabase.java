package samples.linhtruong.com.app.uireactive.database;

import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import samples.linhtruong.com.app.uireactive.data.UpdateNotify;
import samples.linhtruong.com.base.database.Database;

/**
 * Created by Truong on 9/27/16 - 15:16.
 * Description:
 */

public class RxDatabase {

    private final Database mDatabase;
    private SerializedSubject<UpdateNotify, UpdateNotify> mNotifySubject;

    public RxDatabase(Database database) {
        mDatabase = database;
        mNotifySubject = new SerializedSubject<>(PublishSubject.<UpdateNotify>create());
    }

    public SerializedSubject<UpdateNotify, UpdateNotify> getNotifySubject() {
        return mNotifySubject;
    }

    public Database getDatabase() {
        return mDatabase;
    }
}
