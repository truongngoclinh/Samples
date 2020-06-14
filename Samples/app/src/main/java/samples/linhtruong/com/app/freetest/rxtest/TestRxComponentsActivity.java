package samples.linhtruong.com.app.freetest.rxtest;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import samples.linhtruong.com.app.R;
import samples.linhtruong.com.base.BaseActivity;
import samples.linhtruong.com.utils.LogUtils;

/**
 * Created by linhtruong on 11/29/16 - 14:57.
 * Description:
 */

@EActivity
public class TestRxComponentsActivity extends BaseActivity {


    @ViewById(R.id.btnPushEvent)
    Button mBtn;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient mClient;

    @Click(R.id.btnPushEvent)
    void onClick() {
//        testConnectableObservable();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rxtest);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
/*
        Observer<FeedResponse> listObserver = new Observer<FeedResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(FeedResponse feedResponse) {

            }
        };

        getRingtoneData().subscribe(listObserver);*/
    }

   /* private Observable<List<Ringtone>> getRingToneObservable() {
        Observable.create(new Observable.OnSubscribe<List<Ringtone>>() {
            @Override
            public void call(Subscriber<? super List<Ringtone>> subscriber) {
                subscriber.onNext(getListRingtone());
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }*/

    private void testConnectableObservable() {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                LogUtils.d("call: init observable");
                subscriber.onNext("str1");
                subscriber.add(new Subscription() {
                    @Override
                    public void unsubscribe() {
                        LogUtils.d("unsubscribe: ");
                    }

                    @Override
                    public boolean isUnsubscribed() {
                        return false;
                    }
                });
            }
        }).publish().refCount();

        Subscription subscription1 = observable.subscribe();
        LogUtils.d("testConnectableObservable: 1 subscribed");
        Subscription subscription2 = observable.subscribe();
        LogUtils.d("testConnectableObservable: 2 subscribed");
        Subscription subscription3 = observable.subscribe();
        LogUtils.d("testConnectableObservable: 3 subscribed");
        subscription1.unsubscribe();
        LogUtils.d("testConnectableObservable: 1 unsubscribed");
        subscription2.unsubscribe();
        LogUtils.d("testConnectableObservable: 2 unsubscribed");
        subscription2.unsubscribe();
        LogUtils.d("testConnectableObservable: 3 unsubscribed");
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("TestRxComponents Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient.connect();
        AppIndex.AppIndexApi.start(mClient, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(mClient, getIndexApiAction());
        mClient.disconnect();
    }
}
