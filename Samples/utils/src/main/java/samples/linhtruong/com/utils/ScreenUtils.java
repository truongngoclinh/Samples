package samples.linhtruong.com.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 1/4/17 - 14:04.
 * @organization VED
 */

public class ScreenUtils {

    public static int SCREEN_HEIGHT;
    public static int SCREEN_WIDTH;
    public static float DENSITY;

    public static int dp1;
    public static int dp2;
    public static int dp4;
    public static int dp10;

    public static void init(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        DENSITY = metrics.density;
        SCREEN_HEIGHT = metrics.heightPixels;
        SCREEN_WIDTH = metrics.widthPixels;

        dp1 = getPx(dp1);
        dp2 = getPx(dp2);
        dp4 = getPx(dp4);
        dp10 = getPx(dp10);
    }

    public static int getPx(int dp) {
       return (int) (dp * DENSITY + 0.5);
    }

}
