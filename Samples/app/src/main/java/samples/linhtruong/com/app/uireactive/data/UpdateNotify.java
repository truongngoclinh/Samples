package samples.linhtruong.com.app.uireactive.data;

import io.realm.RealmObject;

/**
 * Created by Truong on 9/27/16 - 11:11.
 * Description: contain updated schema  fcgjnk
 */

public class UpdateNotify {

    public final Class<? extends RealmObject> schema;

    public UpdateNotify(Class<? extends RealmObject> schema) {
        this.schema = schema;
    }
}
