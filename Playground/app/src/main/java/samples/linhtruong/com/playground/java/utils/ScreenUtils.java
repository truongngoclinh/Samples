package samples.linhtruong.com.playground.java.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 8/21/18 - 21:07.
 * @organization VED
 */
public class ScreenUtils {
    public static int dpToPx(int dp, Context context) {
        Resources r = context.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
