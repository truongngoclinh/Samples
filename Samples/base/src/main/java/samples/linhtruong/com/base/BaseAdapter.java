package samples.linhtruong.com.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * T DESCRIPTION
 *
 * @author linhtruong
 * @date 1/16/17 - 23:55.
 * @organization VED
 */

public abstract class BaseAdapter<T, H extends BaseAdapter.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected List<T> mData = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return createHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        T data = mData.get(position);
        ((ViewHolder) holder).bindData(data);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<? extends T> data) {
        mData.clear();
        if (data != null) {
            mData.addAll(data);
        }

        notifyDataSetChanged();
    }

    public abstract H createHolder(ViewGroup parent, int viewType);

    public static abstract class ViewHolder<T> extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void bindData(T data);
    }
}
