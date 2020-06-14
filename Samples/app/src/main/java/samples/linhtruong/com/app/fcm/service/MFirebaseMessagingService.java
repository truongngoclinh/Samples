package samples.linhtruong.com.app.fcm.service;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import samples.linhtruong.com.app.AppStateManager;
import samples.linhtruong.com.app.fcm.activity.FirebaseTestActivity;
import samples.linhtruong.com.app.fcm.app.Config;
import samples.linhtruong.com.app.fcm.utils.NotificationUtils;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 2/16/17 - 15:44.
 * @organization VED
 */

public class MFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MFirebaseMessagingService";

    private NotificationUtils mNotificationUtils;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(TAG, "onMessageReceived: From: " + remoteMessage.getFrom());

        if (remoteMessage == null) {
            return;
        }

        // Check if msg contains a notification payload
        if (remoteMessage.getNotification() != null) {
            Log.e(TAG, "onMessageReceived: Notification Body: " + remoteMessage.getNotification().getBody());
            handleNotification(remoteMessage.getNotification().getBody());
        }


        // Check if msg contains a data payload
        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "onMessageReceived: Data Payload: " + remoteMessage.getData().toString());

            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(remoteMessage.getData().toString());
                handleDataMessage(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleNotification(String message) {
        Context context = getApplicationContext();
        if (!AppStateManager.isAppInBackground(context)) {
            // app in foreground, broadcast the push message
            Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
            pushNotification.putExtra(Config.MESSAGE, message);
            LocalBroadcastManager.getInstance(context).sendBroadcast(pushNotification);

            NotificationUtils notificationUtils = new NotificationUtils(context);
            notificationUtils.playNotificationSound();
        } else {
            // if app in background, firebase itself handle the notification
        }
    }

    public void handleDataMessage(JSONObject json) {
        Log.e(TAG, "handleDataMessage: push json: " + json.toString());

        try {
            JSONObject data = json.getJSONObject("data");

            String title = data.getString(("title"));
            String message = data.getString("message");
            boolean isBackground = data.getBoolean("is_background");
            String imageUrl = data.getString("image");
            String timestamp = data.getString("timestamp");
            JSONObject payload = data.getJSONObject("payload");

            if (!AppStateManager.isAppInBackground(getApplicationContext())) {
                // app in foreground, broad the push message
                Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
                pushNotification.putExtra(Config.MESSAGE, message);
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(pushNotification);
            } else {
                // app in background, show the notification in notification tray
                Intent intent = new Intent(getApplicationContext(), FirebaseTestActivity.class);
                intent.putExtra(Config.MESSAGE, message);

                if (TextUtils.isEmpty(imageUrl)) {
                    showNotificationMessage(getApplicationContext(), title, message, timestamp, intent);
                } else {
                    showNotificationMessageWithBigImage(getApplicationContext(), title, message, timestamp, intent, imageUrl);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Showing notification with text only
     */
    private void showNotificationMessage(Context context, String title, String message, String timestamp, Intent intent) {
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mNotificationUtils = new NotificationUtils(context);
        mNotificationUtils.showNotificationMessage(title, message, timestamp, intent);
    }

    /**
     * Showing notification with text and image
     */
    private void showNotificationMessageWithBigImage(Context context, String title, String message, String timestamp, Intent intent, String imageUrl) {
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mNotificationUtils = new NotificationUtils(context);
        mNotificationUtils.showNotificationMessage(title, message, timestamp, intent, imageUrl);
    }
}
