package samples.linhtruong.com.playground.java.google_arch.test.room_test;

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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 8/22/18 - 14:49.
 * @organization VED
 */
public class RoomTestActivity extends BaseActivity {
    private ProgressBar mProgressBar;
    private ListView mListView;
    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> mData;
    private ExecutorService mExecutorService = Executors.newFixedThreadPool(1);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_room_test);

        initViews();
        initData();
    }

    private void initData() {
        mData = new ArrayList<>();
        RepoDAO repoDAO = RepoDatabase.getInstance(this).getRepoDAO();

        mProgressBar.setVisibility(View.VISIBLE);
        mExecutorService.submit(() -> {
            repoDAO.insert(new Repo("1", "Linh", "Truong"));
            repoDAO.insert(new Repo("2", "Hang", "Pham"));
            repoDAO.insert(new Repo("3", "Hai", "Ta"));
            repoDAO.insert(new Repo("4", "Quyet", "Van"));
        });

        Callable<ArrayList<String>> task = () -> {
            ArrayList<String> result = new ArrayList<>();
            List<Repo> repoList = repoDAO.getAllRepos();
            for (Repo repo : repoList) {
                result.add(repo.firstName);
            }

            Log.d(AppConstants.TAG, "query records: " + result.size());
            return result;
        };

        Future<ArrayList<String>> future = mExecutorService.submit(task);
        while (!future.isDone()) {
            Log.d(AppConstants.TAG, "Task is not done...");
        }

        try {
            ArrayList<String> result = future.get();
            if (result.size() > 0) {
                mData.addAll(result);
                mAdapter.addAll(mData);
            }
        } catch (Exception e) {
            //
        } finally {
            mProgressBar.setVisibility(View.INVISIBLE);
        }

        mExecutorService.shutdown();
    }

    private void initViews() {
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        mListView = findViewById(R.id.listView);
        mListView.setAdapter(mAdapter);

        mProgressBar = findViewById(R.id.progress_bar);
    }
}
