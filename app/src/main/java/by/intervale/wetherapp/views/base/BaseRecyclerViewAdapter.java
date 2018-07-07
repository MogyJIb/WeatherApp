package by.intervale.wetherapp.views.base;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseRecyclerViewAdapter<T, H extends BaseRecyclerViewAdapter.BaseViewHolder<T>> extends RecyclerView.Adapter<H> {
    protected List<T> mItems;
    protected BaseViewHolder.OnItemClickListener<T> mOnItemClickListener;

    public BaseRecyclerViewAdapter() {
        mItems = new ArrayList<>();
    }

    @Override
    public void onBindViewHolder(final H holder, int position) {
        holder.mOnClickListener = mOnItemClickListener;
        holder.mItem = mItems.get(position);

        holder.bind();
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void updateData(List<T> items){
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCallback(mItems, items));
        this.mItems = items;
        diffResult.dispatchUpdatesTo(this);
    }

    public void clear(){
        this.mItems.clear();
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(BaseViewHolder.OnItemClickListener<T> onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }

    public abstract static class BaseViewHolder<T> extends RecyclerView.ViewHolder {
        protected CompositeDisposable mDisposables;

        public T mItem;
        public OnItemClickListener<T> mOnClickListener;

        public BaseViewHolder(View view) {
            super(view);
            mDisposables = new CompositeDisposable();
            ButterKnife.bind(this,view);
        }

        public abstract void bind();

        public void unbind(){
            mDisposables.dispose();
        }

        public interface OnItemClickListener<T>{
            void onItemClicked(T item);
        }
    }

    public static class DiffCallback<T extends Comparable<T>> extends DiffUtil.Callback {

        private List<T> oldItems;
        private List<T> newItems;

        public DiffCallback(List<T> oldItems, List<T> newItems) {
            this.oldItems = oldItems;
            this.newItems = newItems;
        }

        @Override
        public int getOldListSize() {
            return oldItems.size();
        }

        @Override
        public int getNewListSize() {
            return newItems.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldItems.get(oldItemPosition)
                    .compareTo(newItems.get(newItemPosition)) == 0;
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldItems.get(oldItemPosition).equals(newItems.get(newItemPosition));
        }
    }
}
