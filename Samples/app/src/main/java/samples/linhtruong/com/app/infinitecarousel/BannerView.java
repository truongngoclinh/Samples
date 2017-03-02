package samples.linhtruong.com.app.infinitecarousel;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import java.util.ArrayList;
import java.util.List;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/2/17 - 14:17.
 * @organization VED
 */

public class BannerView extends LinearLayout {

    private final int ROTATE_INTERVAL_MILLS = 3000;
    private BannerPager mPager;
    private BannerAdapter mAdapter;

   /* private EventSubscriber mOnViewHide = new EventUISubscriber() {
        @Override
        public void onEvent(Event event) {
            mPager.stopAutoScroll();
        }
    };

    private EventSubscriber mOnViewShow = new EventUISubscriber() {
        @Override
        public void onEvent(Event event) {
            mPager.startAutoScroll();
        }
    };*/

    public BannerView(Context context) {
        super(context);
        setOrientation(VERTICAL);

        mAdapter = new BannerAdapter(context);
        mPager = new BannerPager(context);
        mPager.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mPager.setInterval(ROTATE_INTERVAL_MILLS);
        mPager.setAdapter(mAdapter);
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    mAdapter.onPageScrollStateChangedToIdle(mPager);
                }
            }
        });

        addView(mPager);
    }

    public void setData(List<Banner> banners, OnClickListener onClickListener) {
        mAdapter.setClickListener(onClickListener);
        mAdapter.setBanner(banners);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mPager.startAutoScroll();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mPager.stopAutoScroll();
    }

    public void refreshView() {
        mPager.stopAutoScroll();
        List<Banner> data = new ArrayList<>();

        mAdapter.setBanner(data);
        mPager.startAutoScroll();
    }

    public void startAutoScroll() {
        mPager.startAutoScroll();
    }

    public void stopAutoScroll() {
        mPager.stopAutoScroll();
    }
}
