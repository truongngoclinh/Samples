package samples.linhtruong.com.playground.java.google_arch.test.livedata_test;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;
import android.util.Log;
import samples.linhtruong.com.playground.AppConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 8/22/18 - 11:43.
 * @organization VED
 */
public class FruitsModel extends ViewModel {
    private MutableLiveData<List<String>> fruitList;
    private OnProgressbarStateChanged progressbarStateChanged;

    public MutableLiveData<List<String>> getFruitList() {
        Log.d(AppConstants.TAG, "getFruitList: ");
        if (fruitList == null) {
            fruitList = new MutableLiveData<>();
            loadFruits();
        }

        return fruitList;
    }

    private void loadFruits() {
        progressbarStateChanged.show();
        // fake async
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<String> data = new ArrayList<>();
                data.add("Mango");
                data.add("Apple");
                data.add("Orange");
                data.add("Banana");
                data.add("Grapes");
                long seed = System.nanoTime();
                Collections.shuffle(data, new Random(seed));

                // mainthread execution only
                fruitList.setValue(data);
                progressbarStateChanged.hide();
            }
        }, 5000);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(AppConstants.TAG, "onCleared: ");
    }

    public void setCallback(OnProgressbarStateChanged onProgressbarStateChanged) {
        progressbarStateChanged = onProgressbarStateChanged;
    }

    public interface OnProgressbarStateChanged {
        void show();
        void hide();
    }
}
