package samples.linhtruong.com.playground.java.google_arch.test.viewmodel_test;

import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 8/22/18 - 11:06.
 * @organization VED
 */
public class UsersViewModel extends ViewModel {
    private List<User> userList;

    public List<User> getUserList() {
        if (userList == null) {
            userList = loadUsers();
        }

        return userList;
    }

    private List<User> loadUsers() {
        ArrayList<User> userList = new ArrayList<>();
        User user = new User("Linh", "Truong");
        userList.add(user);

        return userList;
    }
}
