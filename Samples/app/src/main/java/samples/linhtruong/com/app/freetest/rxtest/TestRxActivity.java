package samples.linhtruong.com.app.freetest.rxtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.subjects.PublishSubject;
import samples.linhtruong.com.app.R;
import samples.linhtruong.com.base.BaseActivity;
import samples.linhtruong.com.utils.LogUtils;

/**
 * Created by Truong on 9/26/16 - 20:05.
 * Description:
 */

@EActivity
public class TestRxActivity extends BaseActivity {

    @ViewById(R.id.btnPushEvent)
    Button mBtnPush;

    @ViewById(R.id.btnSubscribe)
    Button mBtnSubscribe;

    private PublishSubject<String> subject = PublishSubject.create();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxtest);

      /*  range(5, 3).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.d("onNext: " + integer);
            }
        });*/

     /*   Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(final Subscriber<? super Integer> subscriber) {
                LogUtils.d("Start emitting");
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        while (!subscribedesd.isUnsubscribed()) {p
                            LogUtils.d("pushed event at thread: " + Thread.currentThread());
                            subscriber.onNext(1);
                        }
                    }
                };

                final Thread t = new Thread(r);
                t.start();
                subscriber.add(new Subscription() {
                    @Override
                    public void unsubscribe() {
                        t.interrupt();
                    }

                    @Override
                    public boolean isUnsubscribed() {
                        return false;
                    }
                });
            }
        });

        final Subscriber<Integer> subscriber = new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.d("Callback: onNext: " + integer + " at thread: " + Thread.currentThread());
            }
        };

        observable.subscribe(subscriber);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                subscriber.unsubscribe();
            }
        }, 1000);


      *//*  range(5, 3).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.d("onNext: " + integer);
            }
        });*//*
        Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(final Subscriber<? super Integer> subscriber) {
                LogUtils.d("Start emitting");
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        while (!subscriber.isUnsubscribed()) {
                            LogUtils.d("pushed event at thread: " + Thread.currentThread());
                            subscriber.onNext(1);
                        }
                    }
                };

                final Thread t = new Thread(r);
                t.start();
                subscriber.add(new Subscription() {
                    @Override
                    public void unsubscribe() {
                        t.interrupt();
                    }

                    @Override
                    public boolean isUnsubscribed() {
                        return false;
                    }
                });
            }
        });

        final Subscriber<Integer> subscriber = new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.d("Callback: onNext: " + integer + " at thread: " + Thread.currentThread());
            }
        };

        observable.subscribe(subscriber);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                subscriber.unsubscribe();
            }
        }, 1000);
*/
    }

    @Click(R.id.btnPushEvent)
    void onClickBtnTest() {
        LogUtils.d("Btn push clicked!");
        subject.onNext("test subject!");
    }

    @Click(R.id.btnSubscribe)
    void onClickBtnSubscribe() {
        LogUtils.d("Btn subscribe clicked!");
        observe().subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                LogUtils.d("Receive event: " + s);
            }
        });
    }

    Observable<String> observe() {
        return subject;
    }

    private Observable<Integer> range(final int n, final int r) {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                LogUtils.d("start emitting");
                for (int i = n; ; i++) {
                    if (i <= n + r) {
                        subscriber.onNext(i);
                    } else {
                        break;
                    }
                }

                subscriber.onCompleted();
            }
        });
    }
}
