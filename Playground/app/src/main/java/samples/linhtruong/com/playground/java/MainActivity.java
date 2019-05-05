package samples.linhtruong.com.playground.java;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import samples.linhtruong.com.playground.R;
import samples.linhtruong.com.playground.java.base.BaseActivity;
import samples.linhtruong.com.playground.java.helpers.Navigators;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 8/21/18 - 10:17.
 * @organization VED
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        findViewById(R.id.btnDataBindingTest).setOnClickListener(this);
        findViewById(R.id.btnProfileSample).setOnClickListener(this);
        findViewById(R.id.btnViewModelTest).setOnClickListener(this);
        findViewById(R.id.btnFruitsSample).setOnClickListener(this);
        findViewById(R.id.btnRoomTest).setOnClickListener(this);
        findViewById(R.id.btnCardViewTest).setOnClickListener(this);
        findViewById(R.id.btnLiveDataServiceTest).setOnClickListener(this);
        findViewById(R.id.btnCustomViewTest).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDataBindingTest:
                Navigators.navigateDataBindingTestActivity(this);
                break;

            case R.id.btnProfileSample:
                Navigators.navigateProfileSampleActivity(this);
                break;

            case R.id.btnViewModelTest:
                Navigators.navigateViewModelTestActivity(this);
                break;

            case R.id.btnFruitsSample:
                Navigators.navigateFruitsActivity(this);
                break;

            case R.id.btnCardViewTest:
                Navigators.navigateCardViewTestActivity(this);
                break;

            case R.id.btnRoomTest:
                Navigators.navigateRoomTestActivity(this);
                break;

            case R.id.btnLiveDataServiceTest:
                Navigators.navigateLiveDataServiceTestActivity(this);
                break;

            case R.id.btnCustomViewTest:
                Navigators.navigateCustomViewTestAcitivyt(this);
                break;

            default:
                break;
        }
    }
}
