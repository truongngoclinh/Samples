package samples.linhtruong.com.app.fcm.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import samples.linhtruong.com.app.R;
import samples.linhtruong.com.app.fcm.app.Config;
import samples.linhtruong.com.app.fcm.utils.NotificationUtils;
import samples.linhtruong.com.base.BaseActivity;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 2/16/17 - 16:10.
 * @organization VED
 */

@EActivity
public class FirebaseTestActivity extends BaseActivity {

    private static final String TAG = "FirebaseTestActivity";

    @ViewById(R.id.txt_push_message)
    TextView mTxtPushMessage;

    @ViewById(R.id.txt_reg_id)
    TextView mTxtRegid;

    private BroadcastReceiver mRegistrationReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_firebase_test);
    }

    @AfterViews
    protected void afterView() {
        mRegistrationReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to 'global' topic to receive app wide notification
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                    displayFirebaseRegId();
                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received
                    String message = intent.getStringExtra(Config.MESSAGE);
                    Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();

                    mTxtPushMessage.setText(message);
                }

            }
        };

        displayFirebaseRegId();
    }


    private void displayFirebaseRegId() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = preferences.getString(Config.SHARED_PREF_TOKEN_KEY, null);

        Log.e(TAG, "displayFirebaseRegId: " + regId);

        if (!TextUtils.isEmpty(regId)) {
            mTxtRegid.setText("Firebase Reg Id: " + regId);
        } else {
            mTxtRegid.setText("Firebase Reg Id is not received yet!");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationReceiver, new IntentFilter(Config.REGISTRATION_COMPLETE));
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationReceiver, new IntentFilter(Config.PUSH_NOTIFICATION));

        NotificationUtils.clearNotifications(this);
    }


    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationReceiver);

        super.onPause();
    }
}
