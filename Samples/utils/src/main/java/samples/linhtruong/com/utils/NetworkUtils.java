package samples.linhtruong.com.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.PowerManager;
import android.telephony.TelephonyManager;

/**
 * Created by Truong on 9/26/16 - 22:47.
 * Description:
 */

public class NetworkUtils {

    private static Context sCtx;
    private static final String TAG = NetworkUtils.class.getName();

    public static void init(Context context) {
        sCtx = context.getApplicationContext();
    }

    public static Result check() {
        return new Result(getNetworkInfo(sCtx));
    }

    private static NetworkInfo getNetworkInfo(Context context) {
        if (isDozing(context)) {
            LogUtils.d(TAG, "checkNetwork: isDozing");
            return null;
        }

        ConnectivityManager cm = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        if (cm == null) {
            LogUtils.d("checkNetwork: ConnectivityManager is null !!!");
            return null;
        }
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            LogUtils.d("checkNetwork: networkInfo is null");
            return null;
        }
        LogUtils.d(TAG, "checkNetwork: state=%d type=%s connected=%s", ni.getState(), ni.getTypeName(), ni.isConnected());
        return ni;
    }

    /**
     * Returns true if the device is in Doze/Idle mode. Should be called before checking the network connection because
     * the ConnectionManager may falsely report the device is connected in Doze mode.
     */
    @TargetApi(Build.VERSION_CODES.M)
    public static boolean isDozing(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            return powerManager.isDeviceIdleMode();
        } else {
            return false;
        }
    }

    public final static class Result {

        private final NetworkInfo info;

        public Result(NetworkInfo info) {
            this.info = info;
        }

        public boolean isConnected() {
            return info != null && info.isConnected();
        }

        public boolean isWifi() {
            return isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI;
        }

        public boolean isMobile() {
            return isConnected() && info.getType() == ConnectivityManager.TYPE_MOBILE;
        }

        public boolean isFast() {
            return isConnected() && isConnectionFast(info.getType(), info.getSubtype());
        }

        private boolean isConnectionFast(int type, int subType) {
            if (type == ConnectivityManager.TYPE_WIFI) {
                return true;
            } else if (type == ConnectivityManager.TYPE_MOBILE) {
                switch (subType) {
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                        return false; // ~ 50-100 kbps
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                        return false; // ~ 14-64 kbps
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                        return false; // ~ 50-100 kbps
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                        return true; // ~ 400-1000 kbps
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                        return true; // ~ 600-1400 kbps
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                        return false; // ~ 100 kbps
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                        return true; // ~ 2-14 Mbps
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                        return true; // ~ 700-1700 kbps
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                        return true; // ~ 1-23 Mbps
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                        return true; // ~ 400-7000 kbps
            /*
             * Above API level 7, make sure to set android:targetSdkVersion
             * to appropriate level to use these
             */
                    case TelephonyManager.NETWORK_TYPE_EHRPD: // API level 11
                        return true; // ~ 1-2 Mbps
                    case TelephonyManager.NETWORK_TYPE_EVDO_B: // API level 9
                        return true; // ~ 5 Mbps
                    case TelephonyManager.NETWORK_TYPE_HSPAP: // API level 13
                        return true; // ~ 10-20 Mbps
                    case TelephonyManager.NETWORK_TYPE_IDEN: // API level 8
                        return false; // ~25 kbps
                    case TelephonyManager.NETWORK_TYPE_LTE: // API level 11
                        return true; // ~ 10+ Mbps
                    // Unknown
                    case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                    default:
                        return false;
                }
            } else {
                return false;
            }
        }
    }
}

