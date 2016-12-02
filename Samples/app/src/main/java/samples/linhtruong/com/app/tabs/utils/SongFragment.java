package samples.linhtruong.com.app.tabs.utils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import samples.linhtruong.com.app.R;
import samples.linhtruong.com.base.BaseFragment;

/**
 * Created by linhtruong on 12/2/16 - 10:54.
 * Description:
 */

public class SongFragment extends BaseFragment {

    private static final String TAG = "SongFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View root = inflater.inflate(R.layout.activity_tabhost_songs_fragment, container, false);

        return root;
    }
}
