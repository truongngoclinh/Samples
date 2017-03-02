package samples.linhtruong.com.app.freetest;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

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

    @ViewById(R.id.lstView)
    ListView mListView;

    @Click(R.id.btnRefresh)
    void onClick() {
        List<String> data = new ArrayList<>();
        data.add("0");
        data.add("1");
        data.add("2");
        data.add("3");
        mAdapter.refresh(data);
    }

    MyAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_so_test_list_notifydatasetchanged);
    }

    @AfterViews
    void afterView() {
//        animateTitle();
        List<String> data = new ArrayList<>();
        data.add("0");
        data.add("1");
        mAdapter = new MyAdapter(this, data);
        mListView.setAdapter(mAdapter);
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

    private class MyAdapter extends BaseAdapter {

        private List<String> mData;
        private Context mContext;

        public MyAdapter(Context context, List<String> data) {
            mContext = context;
            mData = data;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int i) {
            return mData.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.activity_so_list_notifydatasetchanged_item, null);
            TextView textView = (TextView) v.findViewById(R.id.txtView);
            textView.setText(mData.get(i));
            return v;
        }

        public void refresh(List<String> newData) {
            mData.clear();
            mData.addAll(newData);
            notifyDataSetChanged();
        }
    }
}

