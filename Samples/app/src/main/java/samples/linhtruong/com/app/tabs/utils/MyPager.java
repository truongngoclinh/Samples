package samples.linhtruong.com.app.tabs.utils;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by linhtruong on 12/2/16 - 15:28.
 * Description:
 */

public class MyPager extends ViewPager {

    private boolean enable = false;

    public MyPager(Context context) {
        super(context);
    }

    public MyPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (this.enable) {
            return super.onTouchEvent(ev);
        }

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (this.enable) {
            return super.onInterceptTouchEvent(ev);
        }

        return false;
    }

    public void setPagingEnabled(boolean enabled) {
        this.enable = enabled;
    }
}
