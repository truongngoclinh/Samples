package samples.linhtruong.com.app.eventbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import samples.linhtruong.com.app.R;
import samples.linhtruong.com.base.BaseActivity;
import samples.linhtruong.com.utils.LogUtils;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 2/6/17 - 16:33.
 * @organization VED
 */

@EActivity
public class TestNotificationAtivity extends BaseActivity {

    private EventId mEventId;

    @ViewById(R.id.txtView)
    TextView mTextView;

    @ViewById(R.id.fireBtn)
    Button mFireButton;

    @Click(R.id.fireBtn)
    void OnClick() {
        mEventId = new EventId();
        String data = "push message!";
        EventBus.getInstance().fire(EventConst.TEST.CASE1, new Event(mEventId.getId(), data));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_eventbus);
    }

    @AfterViews
    void afterView() {
        EventBus.getInstance().subscribe(EventConst.TEST.CASE1, mEventSubcriber);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getInstance().unsubscribe(EventConst.TEST.CASE1, mEventSubcriber);
    }

    EventSubscriber mEventSubcriber = new EventUISubscriber() {
        @Override
        public void onEvent(Event event) {
            LogUtils.d("received event with id: " + event.getRequestId().getId() + " data: " + event.getData().toString());
            if (mEventId == null || !mEventId.equals(event.getRequestId())) {
                return;
            }

            mTextView.setText(event.getData().toString());
        }
    };
}
