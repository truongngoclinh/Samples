package samples.linhtruong.com.playground.java.custom_views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import samples.linhtruong.com.playground.R;


/**
 * @author linhtruong
 */
public class FanControllerActivity extends AppCompatActivity {

    private FanView fanView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fan_controller);
        fanView = findViewById(R.id.fanView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        fanView.setSelectionCount(item.getOrder());
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.fan_menu, menu);
        return true;
    }
}
