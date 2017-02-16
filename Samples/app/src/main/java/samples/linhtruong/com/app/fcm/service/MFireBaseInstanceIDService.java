package samples.linhtruong.com.app.fcm.service;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import samples.linhtruong.com.app.fcm.app.Config;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 2/16/17 - 15:38.
 * @organization VED
 */

public class MFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MFirebaseInstanceIDServ";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String refreshToken = FirebaseInstanceId.getInstance().getToken();
        storeRegIdInPref(refreshToken);

        sendRegistrationToServer(refreshToken);

        Intent registrationComplete = new Intent(Config.REGISTRATION_COMPLETE);
        registrationComplete.putExtra(Config.TOKEN, refreshToken);onDestroy();
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

    private void sendRegistrationToServer(String token) {
        Log.d(TAG, "sendRegistrationToServer: " + token);
    }

    private void storeRegIdInPref(String token) {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Config.SHARED_PREF_TOKEN_KEY, token);
        editor.commit();
    }
}
