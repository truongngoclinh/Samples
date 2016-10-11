package samples.linhtruong.com.app.uireactive.database;

import android.content.Context;

import samples.linhtruong.com.app.uireactive.database.module.UserModule;
import samples.linhtruong.com.base.database.Database;

/**
 * Created by Truong on 9/26/16 - 10:09.
 * Description: user's database, identify by id
 */

public class UserDatabase extends Database {

    public static final String USER_PREF = "user_database";
    public static final int SCHEMA_VERSION = 0;

    public UserDatabase(Context context) {
        super(context);
    }


    @Override
    public String getName() {
        return USER_PREF;
    }

    @Override
    public long getSchemaVersion() {
        return SCHEMA_VERSION;
    }

    @Override
    public Object getModule() {
        return new UserModule();
    }
}
