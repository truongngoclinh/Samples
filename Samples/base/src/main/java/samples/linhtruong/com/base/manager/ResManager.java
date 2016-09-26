package samples.linhtruong.com.base.manager;

import android.content.Context;

/**
 * Created by Truong on 9/26/16 - 11:10.
 * Description:
 */

public class ResManager {

    private Context mContext;

    public ResManager(Context context) {
        mContext = context;
    }

    public String string(int stringId) {
        return mContext.getResources().getString(stringId);
    }

    public int dimen(int dimenId) {
        return mContext.getResources().getDimensionPixelSize(dimenId);
    }

    public int color(int colorId) {
        return mContext.getResources().getColor(colorId);
    }
}
