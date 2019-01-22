package samples.linhtruong.com.playground.java.google_arch.test.room_test;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 8/22/18 - 14:44.
 * @organization VED
 */
@Database(entities = {Repo.class}, version = 1, exportSchema = false)
public abstract class RepoDatabase extends RoomDatabase {
    private static final String DB_NAME = "repoDataBase.db";
    private static volatile RepoDatabase instance;

    static synchronized RepoDatabase getInstance(Context context) {
       if (instance == null) {
          instance = create(context);
       }

       return instance;
    }

    private static RepoDatabase create(final Context context) {
        return Room.databaseBuilder(context, RepoDatabase.class, DB_NAME).build();
    }

    public abstract RepoDAO getRepoDAO();
}
