package samples.linhtruong.com.app.drawer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import samples.linhtruong.com.app.R;
import samples.linhtruong.com.base.BaseFragment;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 2/17/17 - 09:50.
 * @organization VED
 */

public class NotificationFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_drawer_test_fragment, container, false);
        ImageView img = (ImageView) v.findViewById(R.id.fragment_img);
        img.setImageResource(R.drawable.ic_notifications_black_24dp);

        return v;
    }
}
