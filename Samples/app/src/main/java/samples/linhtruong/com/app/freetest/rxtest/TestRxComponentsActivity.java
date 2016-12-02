package samples.linhtruong.com.app.freetest.rxtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import samples.linhtruong.com.app.R;
import samples.linhtruong.com.base.BaseActivity;

/**
 * Created by linhtruong on 11/29/16 - 14:57.
 * Description:
 */

@EActivity
public class TestRxComponentsActivity extends BaseActivity {

    private static final String TAG = "TestRxComponentsActivity";

    @ViewById(R.id.btnPushEvent)
    Button mBtn;

    @Click(R.id.btnPushEvent)
    void onClick() {
        Log.d(TAG, "onClick: ");
        testConnectableObservable();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: TestRxComponentsActivity");

        setContentView(R.layout.activity_rxtest);
    }

    private void testConnectableObservable() {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Log.d(TAG, "call: init observable");
                subscriber.onNext("str1");
                subscriber.add(new Subscription() {
                    @Override
                    public void unsubscribe() {
                        Log.d(TAG, "unsubscribe: ");
                    }

                    @Override
                    public boolean isUnsubscribed() {
                        return false;
                    }
                });
            }
        }).publish().refCount();

        Subscription subscription1 = observable.subscribe();
        Log.d(TAG, "testConnectableObservable: 1 subscribed");
        Subscription subscription2 = observable.subscribe();
        Log.d(TAG, "testConnectableObservable: 2 subscribed");
        Subscription subscription3 = observable.subscribe();
        Log.d(TAG, "testConnectableObservable: 3 subscribed");
        subscription1.unsubscribe();
        Log.d(TAG, "testConnectableObservable: 1 unsubscribed");
        subscription2.unsubscribe();
        Log.d(TAG, "testConnectableObservable: 2 unsubscribed");
        subscription2.unsubscribe();
        Log.d(TAG, "testConnectableObservable: 3 unsubscribed");
    }
}
