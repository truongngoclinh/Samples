package samples.linhtruong.com.base.database.schema;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Truong on 9/23/16 - 23:44.
 * Description: user schema
 */

public class User extends RealmObject {

    @PrimaryKey
    public int id;

    public String name;
    public int age;
}
