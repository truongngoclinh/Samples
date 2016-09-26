package samples.linhtruong.com.base.database.module;

import io.realm.annotations.RealmModule;
import samples.linhtruong.com.base.database.schema.User;

/**
 * Created by Truong on 9/26/16 - 10:32.
 * Description:
 */

@RealmModule(classes = {User.class})
public class UserModule {
}
