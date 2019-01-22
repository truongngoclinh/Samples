package samples.linhtruong.com.playground.java.google_arch.test.viewmodel_test;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import samples.linhtruong.com.playground.R;
import samples.linhtruong.com.playground.java.base.BaseActivity;

import java.util.List;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 8/22/18 - 11:08.
 * @organization VED
 */
public class UsersActivity extends BaseActivity {
    private TextView firstName;
    private TextView lastName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_test_view_model);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);


        UsersViewModel usersViewModel = ViewModelProviders.of(this).get(UsersViewModel.class);
        showUsers(usersViewModel.getUserList());
    }

    private void showUsers(List<User> userList) {
        User user = userList.get(0);
        firstName.setText(user.firstName);
        lastName.setText(user.lastName);
    }
}
