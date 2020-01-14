package samples.linhtruong.com.base.database;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Truong on 9/24/16 - 00:26.
 * Description:
 */

public abstract class Database {

    private Context mContext;
    private RealmConfiguration mConfig;

    public Database(Context context) {
        mContext = context;
        Realm.init(mContext);
    }

    public Realm getInstance() {
        if (mConfig != null) {
            synchronized (this) {
                if (mConfig != null) {
                    mConfig = new RealmConfiguration.Builder()
                            .name(getName())
                            .schemaVersion(getSchemaVersion())
                            .modules(getModule())
                            .build();
                }
            }
        }

        return Realm.getInstance(mConfig);
    }

    public abstract String getName();

    public abstract long getSchemaVersion();

    public abstract Object getModule();
}
