package samples.linhtruong.com.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * MODEL DESCRIPTION
 *
 * @author linhtruong
 * @date 1/16/17 - 23:55.
 * @organization VED
 */

public abstract class BaseRecyclerViewAdapter<MODEL, HOLDER extends BaseRecyclerViewAdapter.BaseRecyclerViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected List<MODEL> mData = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return createHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MODEL data = mData.get(position);
        ((BaseRecyclerViewHolder) holder).bindData(data);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<? extends MODEL> data) {
        mData.clear();
        if (data != null) {
            mData.addAll(data);
        }

        notifyDataSetChanged();
    }

    public abstract HOLDER createHolder(ViewGroup parent, int viewType);

    public abstract class BaseRecyclerViewHolder<MODEL> extends RecyclerView.ViewHolder {

        public BaseRecyclerViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void bindData(MODEL data);
    }
}