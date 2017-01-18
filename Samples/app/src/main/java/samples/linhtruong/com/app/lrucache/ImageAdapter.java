package samples.linhtruong.com.app.lrucache;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import samples.linhtruong.com.app.R;
import samples.linhtruong.com.base.BaseAdapter;
import samples.linhtruong.com.base.listener.ItemInteractorListener;
import samples.linhtruong.com.utils.ScreenUtils;

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
        private WeakReference<ImageLoadTask> mTask;

        private ImageHolder(View itemView, final ItemInteractorListener<ImageInfo> listener) {
            super(itemView);
            mImgView = (ImageView) itemView.findViewById(R.id.img);
            mTask = new WeakReference<>(new ImageLoadTask(mImgView));
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
                mTask.get().execute(data.url);
                Log.d(TAG, "bindData: run task: " + data.url);
            } else {
                Log.d(TAG, "bindData: load cache successfuly: " + data.url);
                mImgView.setImageBitmap(bitmap);
            }
        }

        private class ImageLoadTask extends AsyncTask<String, Void, Bitmap> {

            private WeakReference<ImageView> mImgView;
            private ProgressDialog mDialog;
            private String mUrl;

            public ImageLoadTask(ImageView imgView) {
                mImgView = new WeakReference<>(imgView);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                mDialog = new ProgressDialog(itemView.getContext());
                mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                mDialog.setCancelable(false);
                mDialog.show();
            }

            @Override
            protected Bitmap doInBackground(String... strings) {
                mUrl = strings[0];
                Bitmap bitmap = getBitmapFromUrl(mUrl);
                if (bitmap != null) {
                    Log.d(TAG, "doInBackground: save cache with key: " + mUrl + "\n bitmap size (kb) " + bitmap.getByteCount() / 1024);
                    LCache.getInstance().getLruCache().put(mUrl, bitmap);
                } else {
                    Log.d(TAG, "doInBackground: bitmap null");
                }

                return bitmap;
            }

            @Override
            protected void onPostExecute(Bitmap b) {
                super.onPostExecute(b);

                if (b != null) {
                    mImgView.get().setImageBitmap(b);
                }

                if (mDialog.isShowing()) {
                    mDialog.dismiss();
                }
            }

            private Bitmap getBitmapFromUrl(String src) {
                if (!TextUtils.isEmpty(src)) {
                    try {
                        URL url = new URL(src);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setDoInput(true);
                        connection.connect();
                        InputStream inputStream = connection.getInputStream();

                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = true;

                        Bitmap bitmap;
                        if (inputStream.markSupported()) {
                            Log.d(TAG, "getBitmapFromUrl: markSuported = true, mark and reset stream to reuse");
                            inputStream.mark(inputStream.available());

                            BitmapFactory.decodeStream(inputStream, null, options);
                            options.inSampleSize = calculateInSampleSize(options, ScreenUtils.getPx(140), ScreenUtils.getPx(200));
                            options.inJustDecodeBounds = false;

                            inputStream.reset();

                            bitmap = BitmapFactory.decodeStream(inputStream, null, options);
                        } else {
                            Log.d(TAG, "getBitmapFromUrl: markSuported = false");
                            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

                            int nRead;
                            byte[] data = new byte[16384];
                            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                                buffer.write(data, 0, nRead);
                            }

                            data = buffer.toByteArray();
                            buffer.flush();
                            Log.d(TAG, "getBitmapFromUrl: byte array size = " + data.length);

                            BitmapFactory.decodeByteArray(data, 0, data.length, options);
                            options.inSampleSize = calculateInSampleSize(options, ScreenUtils.getPx(140), ScreenUtils.getPx(200));
                            options.inJustDecodeBounds = false;

                            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, options);
                        }

                        inputStream.close();

                        return bitmap;
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                return null;
            }

            private int calculateInSampleSize(BitmapFactory.Options options, int reqHeight, int reqWidth) {
                Log.d(TAG, "calculateInSampleSize: options: " + options.outWidth + ", " + options.outHeight + " req: " + reqWidth + ", " + reqHeight);
                final int height = options.outHeight;
                final int width = options.outWidth;
                int sampleSize = 1;

                if (height > reqHeight || width > reqWidth) {
                    final int halfHeight = height / 2;
                    final int halfWidth = width / 2;

                    while ((halfHeight / sampleSize) >= reqHeight
                            && (halfWidth / sampleSize) >= reqWidth) {
                        sampleSize *= 2;
                    }
                }

                Log.d(TAG, "calculateInSampleSize: " + sampleSize);
                return sampleSize;
            }
        }
    }
}
