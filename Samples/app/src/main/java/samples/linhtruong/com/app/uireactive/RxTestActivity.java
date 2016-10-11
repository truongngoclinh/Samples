package samples.linhtruong.com.app.uireactive;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import samples.linhtruong.com.app.R;
import samples.linhtruong.com.base.BaseActivity;
import samples.linhtruong.com.utils.LogUtils;

/**
 * Created by Truong on 9/26/16 - 20:05.
 * Description:
 */

@EActivity
public class RxTestActivity extends BaseActivity {

    @ViewById(R.id.btnTest)
    Button mBtnTest;

    public ArrayList<String> lstString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rxtest);
        rxTest();
    }

    private void rxTest() {
        Observable onClickObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                mBtnTest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        subscriber.onNext("pressed!");
                    }
                });
            }
        }).debounce(2, TimeUnit.SECONDS);

        Subscription subscription =  onClickObservable.subscribe(new Action1() {
            @Override
            public void call(Object o) {
               LogUtils.d("OnNext: " + o.toString());
            }
        });

    }

    @Click(R.id.btnTest)
    void OnClickBtnTest() {
        LogUtils.d("Btn test clicked!");
    }
}
