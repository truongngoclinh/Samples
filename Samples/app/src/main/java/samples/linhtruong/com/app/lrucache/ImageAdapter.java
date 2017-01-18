package samples.linhtruong.com.app.lrucache;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import samples.linhtruong.com.app.R;
import samples.linhtruong.com.base.BaseAdapter;
import samples.linhtruong.com.base.listener.ItemInteractorListener;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 1/17/17 - 00:33.
 * @organization VED
 */

public class ImageAdapter extends BaseAdapter<ImageInfo, BaseAdapter.ViewHolder<?>> {

    private static final String TAG = "ImageAdapter";

    private static final int TYPE_CONTENT = 1;

    private ItemInteractorListener<ImageInfo> listener;

    public ImageAdapter(ItemInteractorListener<ImageInfo> listener) {
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        Object data = mData.get(position);
        if (data instanceof ImageInfo) {
            return TYPE_CONTENT;
        }

        return super.getItemViewType(position);
    }

    @Override
    public BaseAdapter.ViewHolder<?> createHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_CONTENT:
                return ImageHolder.create(parent.getContext(), listener);

            default:
                return null;
        }
    }

    private static class ImageHolder extends BaseAdapter.ViewHolder<ImageInfo> {

        private ImageView mImgView;
        private final Bitmap mPlaceHolder = null;

        private ImageHolder(View itemView, final ItemInteractorListener<ImageInfo> listener) {
            super(itemView);
            mImgView = (ImageView) itemView.findViewById(R.id.img);

            if (listener != null) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Object object = view.getTag();
                        if (object instanceof ImageInfo) {
                            listener.onItemClick((ImageInfo) object);
                        }
                    }
                });
            }
        }

        public static ImageHolder create(Context context, ItemInteractorListener<ImageInfo> listener) {
            View v = LayoutInflater.from(context).inflate(R.layout.lru_cache_grid_item, null, false);
            return new ImageHolder(v, listener);
        }

        @Override
        public void bindData(ImageInfo data) {
            itemView.setTag(data);
            Bitmap bitmap = (Bitmap) LCache.getInstance().getLruCache().get(data.url);
            if (bitmap == null) {
                Log.d(TAG, "bindData: run task: " + data.url);
                ImageLoadTask.loadBitmap(itemView.getResources(), mPlaceHolder, data.url, mImgView);
            } else {
                Log.d(TAG, "bindData: load cache successfuly: " + data.url);
                mImgView.setImageBitmap(bitmap);
            }
        }

    }
}
