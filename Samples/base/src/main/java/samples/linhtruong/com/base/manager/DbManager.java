package samples.linhtruong.com.base.manager;

import android.content.Context;

import samples.linhtruong.com.base.database.UserDatabase;

/**
 * Created by Truong on 9/26/16 - 10:55.
 * Description:
 */

public class DbManager {

    private Context mContext;
    private UserDatabase mUserDb;

    public DbManager(Context context) {
        mContext = context;
        initUserDb();
    }

    private void initUserDb() {
        if (mUserDb == null) {
            synchronized (this) {
                if (mUserDb == null) {
                   mUserDb = new UserDatabase(mContext);
                }
            }
        }
    }
}
