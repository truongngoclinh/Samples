package samples.linhtruong.com.playground.java.google_arch.test;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 8/21/18 - 16:29.
 * @organization VED
 */
public class DataBindingClickHandlers {
    private final Context mContext;

    public DataBindingClickHandlers(Context context) {
       mContext = context;
    }

    public void onFabClicked(View view) {
        Toast.makeText(mContext, "FAB clicked!", Toast.LENGTH_LONG).show();
    }

    public void onBtnClicked(View view, DataBindingModel user) {
        Toast.makeText(mContext, "Button clicked! params: " + user.getFirstName(), Toast.LENGTH_LONG).show();
        user.setFirstName("Hang");
        user.setLastName("Pham");
    }

    public boolean onBtnLongPressed(View view, DataBindingModel user) {
        Toast.makeText(mContext, "Button long pressed! params: " + user.getFirstName(), Toast.LENGTH_LONG).show();

        return false;
    }
}
