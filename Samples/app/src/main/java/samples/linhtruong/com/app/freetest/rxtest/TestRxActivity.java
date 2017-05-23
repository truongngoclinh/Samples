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
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.subjects.PublishSubject;
import samples.linhtruong.com.app.R;
import samples.linhtruong.com.base.BaseActivity;
import samples.linhtruong.com.utils.LogUtils;

import java.math.BigInteger;
import java.util.ArrayList;
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

    void test() {
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
        Observable.range(1, 10).scan(BigInteger.ONE, new Func2<BigInteger, Integer, BigInteger>() {
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
        });
    }

    @Click(R.id.btnPushEvent)
    void onClickBtnTest() {
        LogUtils.d("Btn push clicked!");
//        subject.onNext("test subject!");
        test();
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
