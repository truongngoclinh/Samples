package samples.linhtruong.com.app.lrucache;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.EActivity;

import samples.linhtruong.com.base.BaseActivity;
import samples.linhtruong.com.base.BaseRecyclerViewAdapter;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 1/16/17 - 23:41.
 * @organization VED
 */

@EActivity
public class LruCacheTestActivity extends BaseActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public class GridImageAdapter extends BaseRecyclerViewAdapter<ImageInfo, BaseRecyclerViewAdapter.BaseRecyclerViewHolder<?>> {

        @Override
        public BaseRecyclerViewHolder<?> createHolder(ViewGroup parent, int viewType) {
            return null;
        }

        private static class ImageHolder extends BaseRecyclerViewHolder<ImageInfo> {

            public ImageHolder(View itemView) {
                super(itemView);
            }

            @Override
            public void bindData(ImageInfo data) {

            }
        }
    }
}
