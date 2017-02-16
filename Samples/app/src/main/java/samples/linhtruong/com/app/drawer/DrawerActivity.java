package samples.linhtruong.com.app.drawer;

import android.app.Activity;
import android.os.Bundle;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import samples.linhtruong.com.app.R;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 2/7/17 - 16:10.
 * @organization VED
 */

@EActivity
public class DrawerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_drawer_test);
    }

    @AfterViews
    void afterView() {

    }
}
