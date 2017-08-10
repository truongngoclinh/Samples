package samples.linhtruong.com.app.freetest.rxtest;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.internal.schedulers.NewThreadWorker;
import rx.internal.util.RxThreadFactory;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import samples.linhtruong.com.app.R;
import samples.linhtruong.com.base.BaseActivity;
import samples.linhtruong.com.utils.LogUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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

    }

    void testabc() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");

        map.get("key1");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.print(entry.getKey() + " -> " + entry.getValue());
        }
    }

    void leotest() {
        final PublishSubject<Integer> subject = PublishSubject.create();
        Observable<Integer> obs = subject
                .subscribeOn(new Scheduler() {
                    @Override
                    public Scheduler.Worker createWorker() {
                        return new NewThreadWorker(new RxThreadFactory("leo-subscribeOn"));
                    }
                }).observeOn(new Scheduler() {
                    @Override
                    public Worker createWorker() {
                        return new NewThreadWorker(new RxThreadFactory("leo-observeon"));
                    }
                });

        subject.subscribeOn(new Scheduler() {
            @Override
            public Worker createWorker() {
                return new NewThreadWorker(new RxThreadFactory("leo-observeon\n"));
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.e("linhln", Thread.currentThread().getName() + " - onComplete() called");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("linhln", Thread.currentThread().getName() + " - onError() called with: e = [" + e + "]");
            }

            @Override
            public void onNext(Integer integer) {
                Log.e("linhln", Thread.currentThread().getName() + " - onNext() called with: integer = [" + "]");
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                subject.onNext(10);
            }
        }, 500);
    }

    void test() {
        final List<Integer> list = Arrays.asList(1, 2, 3, 4);
        for (Integer i : list) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }

        subject = (PublishSubject<String>) subject.subscribeOn(Schedulers.newThread());

        Observable<String> test;
        Observer<String> test2;

        String result = "abc";
        String mesasge = result != null ? result : "empty";
  /*      String[] test = {"1", "2", "3", "4", "5", "6"};
        Observable<String> fast = Observable.interval(2, TimeUnit.SECONDS).map(new Func1<Long, String>() {
            @Override
            public String call(Long aLong) {
                LogUtils.d("fast emitted: " + aLong);
                return "F" + aLong;
            }
        });

        Observable<String> slow = Observable.interval(5, TimeUnit.SECONDS).map(new Func1<Long, String>() {
            @Override
            public String call(Long aLong) {
                LogUtils.d("slow emitted: " + aLong);
                return "S" + aLong;
            }
        });

       *//* Observable.combineLatest(fast, slow, new Func2<String, String, String>() {
            @Override
            public String call(String s, String s2) {
                return s + ":" + s2;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                LogUtils.d(s + "\n");
            }
        });*//*
       fast.withLatestFrom(slow, new Func2<String, String, String>() {
           @Override
           public String call(String s, String s2) {
               return s + ":" + s2;
           }
       }).subscribe(new Action1<String>() {
           @Override
           public void call(String s) {
               LogUtils.d(s + "\n");
           }
       });*/
   /*     Observable.range(1, 10).scan(BigInteger.ONE, new Func2<BigInteger, Integer, BigInteger>() {
            @Override
            public BigInteger call(BigInteger bigInteger, Integer integer) {
                return bigInteger.multiply(BigInteger.valueOf(integer.intValue()));
            }
        }).subscribe(new Observer<BigInteger>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BigInteger bigInteger) {
                LogUtils.d(bigInteger.intValue() + " ");
            }
        });

        LogUtils.d("reduce operator\n:");
        Observable.range(1, 10).reduce(BigInteger.ONE, new Func2<BigInteger, Integer, BigInteger>() {
            @Override
            public BigInteger call(BigInteger bigInteger, Integer integer) {
                return bigInteger.multiply(BigInteger.valueOf(integer.intValue()));
            }
        }).subscribe(new Subscriber<BigInteger>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BigInteger bigInteger) {
                LogUtils.d(bigInteger.intValue());
            }
        });

        LogUtils.d("reduce mutable accumulator\n:");
        Observable.range(1, 10).reduce(new ArrayList<String>(), new Func2<ArrayList<String>, Integer, ArrayList<String>>() {
            @Override
            public ArrayList<String> call(ArrayList<String> strings, Integer integer) {
                strings.add(integer.intValue() + "");
                return strings;
            }
        }).subscribe(new Subscriber<ArrayList<String>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ArrayList<String> strings) {
                LogUtils.d("result: " + strings);
            }
        });*/
/*
        final Observable<Integer> randomIntObs = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                Random random = new Random();
                while (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(random.nextInt(10));
                }
            }
        }).distinct().take(20);

        final Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.d(integer.intValue() + "\n");
            }
        };

        randomIntObs.subscribe(observer);*/

        Observable<Boolean> trueFalse = Observable.just(true, false).repeat();

    }

    @Click(R.id.btnPushEvent)
    void onClickBtnTest() {
        LogUtils.d("Btn push clicked!");
//        subject.onNext("test subject!");
//        test();
        leotest();
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
