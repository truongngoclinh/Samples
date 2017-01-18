package samples.linhtruong.com.app.lrucache;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import samples.linhtruong.com.utils.ScreenUtils;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 1/18/17 - 16:39.
 * @organization VED
 */

public class ImageLoadTask extends AsyncTask<String, Void, Bitmap> {

    private static final String TAG = "ImageLoadTask";

    private WeakReference<ImageView> mImgViewRef;
    public String mUrl;

    public ImageLoadTask(ImageView imgView) {
        mImgViewRef = new WeakReference<>(imgView);
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
        if (isCancelled()) {
            b = null;
        }

        if (b != null && mImgViewRef != null) {
            final ImageView imageView = mImgViewRef.get();
            final ImageLoadTask imageLoadTask = getImageLoadTask(imageView);
            if (this == imageLoadTask && imageView != null) {
                imageView.setImageBitmap(b);
            }
        }
    }


    private static boolean cancelPotentialWork(String url, ImageView imageView) {
        final ImageLoadTask imageLoadTask = getImageLoadTask(imageView);

        if (imageLoadTask != null) {
            String taskUrl = imageLoadTask.mUrl;
            if (!TextUtils.isEmpty(taskUrl)) {
                if (taskUrl.equals(url)) {
                    return false;
                } else {
                    Log.d(TAG, "cancelPotentialWork: cancel task with url: " + taskUrl);
                    imageLoadTask.cancel(true);
                }
            }
        }

        return true;
    }

    private static ImageLoadTask getImageLoadTask(ImageView imageView) {
        if (imageView != null) {
            final Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AsyncDrawable) {
                AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
                return asyncDrawable.getImageLoadTask();
            }
        }

        return null;
    }

    public static void loadBitmap(Resources res, Bitmap placeHolder, String url, ImageView imageView) {
        if (cancelPotentialWork(url, imageView)) {
            final ImageLoadTask imageLoadTask = new ImageLoadTask(imageView);
            final AsyncDrawable asyncDrawable = new AsyncDrawable(imageView.getResources(), placeHolder, imageLoadTask);
            imageView.setImageDrawable(asyncDrawable);
            imageLoadTask.execute(url);
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

    private static class AsyncDrawable extends BitmapDrawable {

        private final WeakReference<ImageLoadTask> mImageLoadTaskRef;

        private AsyncDrawable(Resources res, Bitmap bitmap, ImageLoadTask mImageLoadTask) {
            super(res, bitmap);
            this.mImageLoadTaskRef = new WeakReference<>(mImageLoadTask);
        }

        public ImageLoadTask getImageLoadTask() {
            return mImageLoadTaskRef.get();
        }
    }
}

