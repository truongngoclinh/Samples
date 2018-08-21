package samples.linhtruong.com.playground.java.google_arch.test;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import samples.linhtruong.com.playground.R;
import samples.linhtruong.com.playground.databinding.ActivityTestDataBindingBinding;
import samples.linhtruong.com.playground.java.base.BaseActivity;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 8/21/18 - 15:16.
 * @organization VED
 */
public class DataBindingActivity extends BaseActivity {

    private DataBindingModel mUser;
    private DataBindingClickHandlers mHandlers;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.activity_data_binding_test);
        }

        ActivityTestDataBindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_test_data_binding);
//        mUser = new DataBindingModel("Linh", "Truong");
        mUser = new DataBindingModel();
        mUser.setFirstName("Linh");
        mUser.setLastName("Truong");
        binding.setUser(mUser);

        mHandlers = new DataBindingClickHandlers(this);
        binding.setHandlers(mHandlers);
        binding.content.setHandlers(mHandlers);
    }
}

