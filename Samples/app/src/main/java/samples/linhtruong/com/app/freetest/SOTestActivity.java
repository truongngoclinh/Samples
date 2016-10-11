package samples.linhtruong.com.app.freetest;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import samples.linhtruong.com.app.R;
import samples.linhtruong.com.base.BaseActivity;

/**
 * Created by Truong on 9/28/16 - 10:52.
 * Description:
 */

@EActivity
public class SOTestActivity extends BaseActivity {

    @ViewById(R.id.title_lin)
    LinearLayout mLayoutTitle;

    @ViewById(R.id.content_scroll)
    RelativeLayout mLayoutContent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_so_test);
    }

    @AfterViews
    void afterView() {
        animateTitle();
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void animateTitle() {

        mLayoutContent.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int scrollY = mLayoutContent.getScrollY();
                if (scrollY > 0) {
                    mLayoutTitle.animate().translationX(-1f);
                }
            }
        });

    }
}
