package samples.linhtruong.com.app.lrucache;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import retrofit2.http.POST;
import samples.linhtruong.com.app.R;
import samples.linhtruong.com.base.BaseRecyclerViewAdapter;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 1/17/17 - 00:33.
 * @organization VED
 */

public class GridAdapter extends BaseRecyclerViewAdapter<ImageInfo, BaseRecyclerViewAdapter.BaseRecyclerViewHolder<?>> {

    private final int TYPE_CONTENT = 1;

    @Override
    public BaseRecyclerViewHolder<?> createHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_CONTENT:
                return crea

            default:

        }
    }

    private static class ImageHolder extends BaseRecyclerViewAdapter.BaseRecyclerViewHolder<ImageInfo> {

        private ImageHolder(View itemView) {
            super(itemView);
        }

        public static ImageHolder createHolder(Context context) {
            View v = LayoutInflater.from(context).inflate(R.layout.lru_cache_grid_item, null);
            return new ImageHolder(v);
        }

        @Override
        public void bindData(ImageInfo data, ) {

        }
    }
}
