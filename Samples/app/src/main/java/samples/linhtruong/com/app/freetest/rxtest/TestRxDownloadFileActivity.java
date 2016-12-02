package samples.linhtruong.com.app.freetest.rxtest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;
import samples.linhtruong.com.app.R;
import samples.linhtruong.com.utils.LogUtils;

/**
 * Created by linhtruong on 10/11/16 - 21:04.
 * Description: test okhttp lib
 */

@EActivity
public class TestRxDownloadFileActivity extends Activity {

    private final String URL_IMAGE = "https://drive.google.com/uc?export=download&id=1eubhbGjn442acUb4Clfh8RxHrUP84FdO5w";
    private final String URL_JSON = "https://api.github.com/";

    private OkHttpClient mOkHttpClient;
    private Request mRequest;

    private ProgressDialog mProgressDialog;

    @ViewById(R.id.text)
    protected TextView mText;

    @Click(R.id.btnPushEvent)
    void OnClickBtnTest() {
//        new STask(this).execute();
//        asynchronousCall();
        rxPublishProgress();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxtest);

        initOkHttpClient();
    }

    private void initOkHttpClient() {
        mOkHttpClient = new OkHttpClient();
        mRequest = new Request.Builder().url(URL_JSON).build();
    }

    /**
     * synchronous call
     */
    private class STask extends AsyncTask<Void, Void, Response> {

        Context mContext;

        public STask(Context context) {
            mContext = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressDialog = new ProgressDialog(mContext);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.show();
        }

        @Override
        protected Response doInBackground(Void... voids) {
            Response response = null;
            try {
                response = mOkHttpClient.newCall(mRequest).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(Response response) {
            super.onPostExecute(response);

            if (response.isSuccessful()) {
                mProgressDialog.dismiss();
                LogUtils.d("response successful!");
            } else {
                LogUtils.e(new IOException(response.toString()));
            }
        }
    }

    /**
     * Asynchronous call
     */
    private void asynchronousCall() {
        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String body = response.body().string();

                    // update ui on main thread
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mText.setText(body.substring(0, 50));
                        }
                    });
                }
            }
        });
    }

    private void rxAsynchronousCall() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            subscriber.onNext(response.body().string());
                            subscriber.onCompleted();
                        }
                    }
                });
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(final String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mText.setText(s);
                    }
                });
            }
        });
    }

    private void rxPublishProgress() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setMax(100);
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        mRequest = new Request.Builder().url(URL_IMAGE).build();

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    InputStream inputStream;
                    Response response = mOkHttpClient.newCall(mRequest).execute();
                    if (response.isSuccessful()) {
                        inputStream = response.body().byteStream();
                        long len = response.body().contentLength();

                        String progress = "0";
                        subscriber.onNext(progress);

                        byte[] data = new byte[1024];
                        long total = 0;
                        int count;
                        while ((count = inputStream.read(data)) != -1) {
                            total += count;
                            progress = String.valueOf(total * 100 / len);
                            subscriber.onNext(progress);
                        }

                        inputStream.close();
                        subscriber.onCompleted();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.newThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                LogUtils.d("onCompleted");
                mProgressDialog.dismiss();
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d(e.toString());
                mProgressDialog.dismiss();
            }

            @Override
            public void onNext(final String progress) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mProgressDialog.setProgress(Integer.parseInt(progress));
                    }
                });
            }
        });
    }
}
