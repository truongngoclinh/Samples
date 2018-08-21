package samples.linhtruong.com.playground.java.google_arch.test.databinding_profile_sample.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;
import samples.linhtruong.com.playground.R;
import samples.linhtruong.com.playground.databinding.ActivityProfileBinding;
import samples.linhtruong.com.playground.java.base.BaseActivity;
import samples.linhtruong.com.playground.java.google_arch.test.databinding_profile_sample.model.Post;
import samples.linhtruong.com.playground.java.google_arch.test.databinding_profile_sample.model.User;
import samples.linhtruong.com.playground.java.google_arch.test.databinding_profile_sample.utils.GridSpacingItemDecoration;
import samples.linhtruong.com.playground.java.utils.ScreenUtils;

import java.util.ArrayList;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 8/21/18 - 20:06.
 * @organization VED
 */
public class ProfileActivity extends BaseActivity implements PostAdapter.PostAdapterListener {
    private RecyclerView mRecyclerView;
    private PostAdapter mAdapter;
    private ProfileClickHandlers mHandlers;
    private User mUser;
    private ActivityProfileBinding mProfileBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile);

        Toolbar toolbar = mProfileBinding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.activity_profile_sample);
        getSupportActionBar().setHomeButtonEnabled(true);

        mHandlers = new ProfileClickHandlers(this);

        renderProfile();
        initRecycleView();
    }

    private void initRecycleView() {
        mAdapter = new PostAdapter(getPosts(), this);
        mRecyclerView = mProfileBinding.content.recyclerView;
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(3, ScreenUtils.dpToPx(4, this), true));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setNestedScrollingEnabled(true);
    }

    private ArrayList<Post> getPosts() {
        ArrayList<Post> posts = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            Post post = new Post();
            post.setImageUrl("https://api.androidhive.info/images/nature/" + i + ".jpg");

            posts.add(post);
        }

        return posts;
    }

    private void renderProfile() {
        mUser = new User();
        mUser.setName("Linh Truong");
        mUser.setEmail("linh.truongngoc90@gmail.com");
        mUser.setProfileImage("http://www.artistmanagementnews.com/wp-content/uploads/2017/06/wonderwoman.jpg");
        mUser.setAbout("Dev geek");

        // ObservableField doesn't have setter method, instead will
        // be called using set() method
        mUser.numberOfPosts.set(3400L);
        mUser.numberOfFollowers.set(3050890L);
        mUser.numberOfFollowing.set(150L);


        // display mUser
        mProfileBinding.setUser(mUser);

        // assign click handlers
        mProfileBinding.content.setHandlers(mHandlers);
    }

    @Override
    public void onPostClicked(Post post) {
        Toast.makeText(this, "Post clicked! " + post.getImageUrl(), Toast.LENGTH_SHORT).show();
    }

    public class ProfileClickHandlers {
        private Context mContext;

        public ProfileClickHandlers(Context context) {
            mContext = context;
        }

        public void onFollowingClicked(View view) {
            Toast.makeText(mContext, "Following is clicked!", Toast.LENGTH_SHORT).show();
        }

        public void onFollowersClicked(View view) {
            Toast.makeText(mContext, "Followers is clicked!", Toast.LENGTH_SHORT).show();
        }

        public boolean onProfileImageLongPressed(View view) {
            Toast.makeText(getApplicationContext(), "Profile image long pressed!", Toast.LENGTH_LONG).show();

            return false;
        }

        public void onProfileFabClicked(View view) {
            mUser.setName("Sir David Attenborough");
            mUser.setProfileImage("https://api.androidhive.info/images/nature/david1.jpg");

            // updating ObservableField
            mUser.numberOfPosts.set(5500L);
            mUser.numberOfFollowers.set(5050890L);
            mUser.numberOfFollowing.set(180L);
        }

        public void onPostsClicked(View view) {
            Toast.makeText(mContext, "Posts is clicked!", Toast.LENGTH_SHORT).show();

        }
    }
}
