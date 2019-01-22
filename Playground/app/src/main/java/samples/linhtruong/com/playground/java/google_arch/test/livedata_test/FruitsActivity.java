package samples.linhtruong.com.playground.java.google_arch.test.livedata_test;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import samples.linhtruong.com.playground.AppConstants;
import samples.linhtruong.com.playground.R;
import samples.linhtruong.com.playground.java.base.BaseActivity;


/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 8/22/18 - 11:48.
 * @organization VED
 */
public class FruitsActivity extends BaseActivity implements FruitsModel.OnProgressbarStateChanged {

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_livedata_fruits);

        ListView listView = findViewById(R.id.listView);
        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(listAdapter);

        mProgressBar = findViewById(R.id.progress_bar);

        FruitsModel model = ViewModelProviders.of(this).get(FruitsModel.class);
        model.setCallback(this);
        model.getFruitList().observe(this, fruitList -> {
            Log.d(AppConstants.TAG, "onDataChanged!");
            listAdapter.addAll(fruitList);
        });
    }

    @Override
    public void show() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hide() {
        if (mProgressBar.isShown()) {
            mProgressBar.setVisibility(View.GONE);
        }
    }
}
