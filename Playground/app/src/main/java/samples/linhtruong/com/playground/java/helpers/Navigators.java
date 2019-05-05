package samples.linhtruong.com.playground.java.helpers;

import android.app.Activity;
import android.content.Intent;
import samples.linhtruong.com.playground.java.custom_views.FanControllerActivity;
import samples.linhtruong.com.playground.java.google_arch.test.DataBindingActivity;
import samples.linhtruong.com.playground.java.google_arch.test.databinding_profile_sample.view.ProfileActivity;
import samples.linhtruong.com.playground.java.google_arch.test.livedata_test.FruitsActivity;
import samples.linhtruong.com.playground.java.google_arch.test.room_test.RoomTestActivity;
import samples.linhtruong.com.playground.java.google_arch.test.viewmodel_test.UsersActivity;
import samples.linhtruong.com.playground.java.utils.CardViewActivity;
import samples.linhtruong.com.playground.kotlin.google_arch.livedata.LiveDataActivityTest;

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

    public static void navigateFruitsActivity(Activity activity) {
        activity.startActivity(new Intent(activity, FruitsActivity.class));
    }

    public static void navigateCardViewTestActivity(Activity activity) {
        activity.startActivity(new Intent(activity, CardViewActivity.class));
    }

    public static void navigateRoomTestActivity(Activity activity) {
        activity.startActivity(new Intent(activity, RoomTestActivity.class));
    }

    public static void navigateViewModelTestActivity(Activity activity) {
        activity.startActivity(new Intent(activity, UsersActivity.class));
    }

    public static void navigateLiveDataServiceTestActivity(Activity activity) {
        activity.startActivity(new Intent(activity, LiveDataActivityTest.class));
    }

    public static void navigateCustomViewTestAcitivyt(Activity activity) {
        activity.startActivity(new Intent(activity, FanControllerActivity.class));
    }
}
