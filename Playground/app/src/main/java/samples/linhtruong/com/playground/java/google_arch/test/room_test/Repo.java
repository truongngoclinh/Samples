package samples.linhtruong.com.playground.java.google_arch.test.room_test;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import org.jetbrains.annotations.NotNull;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 8/22/18 - 14:41.
 * @organization VED
 */

@Entity
public class Repo {
    @NotNull
    @PrimaryKey
    public final String id;

    @ColumnInfo
    public final String firstName;

    @ColumnInfo
    public final String lastName;

    public Repo(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
