package samples.linhtruong.com.playground.java.google_arch.test.databinding_profile_sample.view;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import samples.linhtruong.com.playground.R;
import samples.linhtruong.com.playground.databinding.ActivityProfileRowItemBinding;
import samples.linhtruong.com.playground.java.google_arch.test.databinding_profile_sample.model.Post;

import java.util.List;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 8/21/18 - 18:01.
 * @organization VED
 */
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {
    private final PostAdapterListener mPostListener;
    private List<Post> mData;
    private LayoutInflater mLayoutInflater;

    public PostAdapter(List<Post> data, PostAdapterListener listener) {
        mPostListener = listener;
        mData = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(parent.getContext());
        }

        ActivityProfileRowItemBinding binding = DataBindingUtil.inflate(mLayoutInflater, R.layout.activity_profile_row_item, parent, false);

        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mItemBinding.setPost(mData.get(position));
        holder.mItemBinding.thumbnail.setOnClickListener((View v) -> {
                if (mPostListener != null) {
                    mPostListener.onPostClicked(mData.get(position));
                }
            }
        );
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ActivityProfileRowItemBinding mItemBinding;

        public MyViewHolder(ActivityProfileRowItemBinding binding) {
            super(binding.getRoot());
            mItemBinding = binding;
        }
    }

    public interface PostAdapterListener {
        void onPostClicked(Post post);
    }
}
