package samples.linhtruong.com.app.infinitecarousel;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import samples.linhtruong.com.app.R;
import samples.linhtruong.com.utils.LogUtils;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/2/17 - 14:01.
 * @organization VED
 */

public class BannerAdapter extends PagerAdapter {

    private static final int MAX_QUEUE_SIZE = 5;
    private static final int EXTRA_VIEWS_COUNT = 2;

    private final List<Banner> mData = new ArrayList<>();
    private Queue<ImageView> mViewQueue = new LinkedList<>();
    private Context mContext;
    private View.OnClickListener mOnClickListener;

    private int mWidth;
    private int mHeight;

    public BannerAdapter(Context context) {
        mContext = context;

        getViewDimension();
    }

    public void setClickListener(View.OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public void setBanner(List<Banner> data) {
        mData.clear();
        if (data != null) {
            for (Banner banner : data) {
                mData.add(banner);
            }
        }

        LogUtils.d("banners = " + mData.size());
        notifyDataSetChanged();
    }

    private void getViewDimension() {
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mWidth = metrics.widthPixels;
        mHeight = (int) (mWidth * 0.35f);
    }

    @Override
    public int getCount() {
        if (isMultiViews()) {
            return mData.size() + EXTRA_VIEWS_COUNT;
        } else {
            return mData.size();
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (getCount() > 0) {

        }

        int maxQueueSize = Math.max(getCount(), MAX_QUEUE_SIZE);
        while (mViewQueue.size() > maxQueueSize) {
            mViewQueue.poll();
        }
    }

    @Override
    public int getItemPosition(Object object) {
        int pos = 0;
        Object tag = ((View) object).getTag();
        for (Banner banner : mData) {
            if (banner.equals(tag)) {
                return pos;
            }

            pos++;
        }

        return POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ImageView recycleView = (ImageView) object;
        container.removeView(recycleView);
        mViewQueue.add(recycleView);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Banner banner = mData.get(getIndexByPosition(position));
        LogUtils.d("index = " + getIndexByPosition(position) + " banner = " + banner.url);

        ImageView recyclerView = mViewQueue.poll();
        if (recyclerView == null) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.CENTER_VERTICAL;
            recyclerView = new ImageView(mContext);
            recyclerView.setScaleType(ImageView.ScaleType.FIT_XY);
            recyclerView.setLayoutParams(layoutParams);
            recyclerView.setAdjustViewBounds(true);
        }
        recyclerView.setTag(banner);
        recyclerView.setOnClickListener(mOnClickListener);
        Picasso.with(mContext).load(banner.url).resize(mWidth, mHeight).centerInside().placeholder(R.drawable.home_bar_img_banner_empty).into(recyclerView);

        container.addView(recyclerView);
        return recyclerView;
    }

    private int getIndexByPosition(int position) {
        if (isMultiViews()) {
            int index;
            if (position == 0) {
                index = mData.size() - 1;
            } else if (position == getCount() - 1) {
                index = 0;
            } else {
                index = position - 1;
            }

            return index;
        } else {
            return position;
        }
    }

    private boolean isMultiViews() {
        return mData.size() > 1;
    }

    public void onPageScrollStateChangedToIdle(BannerPager bannerPager) {
        if (isMultiViews()) {
            int currentPosition = bannerPager.getCurrentItem();
            if (currentPosition == 0) {
                LogUtils.d("here is fake first item");
                bannerPager.setCurrentItem(mData.size(), false);
            } else if (currentPosition == getCount() - 1) {
                LogUtils.d("here is fake last item");
                bannerPager.setCurrentItem(1, false);
            }
        }
    }
}
