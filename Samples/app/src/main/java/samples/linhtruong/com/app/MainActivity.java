package samples.linhtruong.com.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import org.androidannotations.annotations.EActivity;

import samples.linhtruong.com.app.uireactive.RxActivity_;
import samples.linhtruong.com.app.uireactive.RxTestActivity_;
import samples.linhtruong.com.base.BaseActivity;

@EActivity
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RxActivity_.intent(this).start();
//        RxTestActivity_.intent(this).start();
    }
}
