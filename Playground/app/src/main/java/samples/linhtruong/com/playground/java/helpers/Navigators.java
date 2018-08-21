package samples.linhtruong.com.playground.java.helpers;

import android.app.Activity;
import android.content.Intent;
import samples.linhtruong.com.playground.java.google_arch.test.DataBindingActivity;
import samples.linhtruong.com.playground.java.google_arch.test.databinding_profile_sample.view.ProfileActivity;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 8/21/18 - 15:25.
 * @organization VED
 */
public class Navigators {
    public static void navigateDataBindingTestActivity(Activity activity) {
        activity.startActivity(new Intent(activity, DataBindingActivity.class));
    }

    public static void navigateProfileSampleActivity(Activity activity) {
        activity.startActivity(new Intent(activity, ProfileActivity.class));
    }
}
