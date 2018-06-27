package by.intervale.wetherapp.views.cities;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.intervale.wetherapp.R;
import by.intervale.wetherapp.models.City;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class CityRecyclerViewAdapter extends RecyclerView.Adapter<CityRecyclerViewAdapter.ViewHolder> {

    private List<City> mCities;
    private ViewHolder.OnItemClickListener mOnItemClickListener;

    public CityRecyclerViewAdapter() {
        mCities = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_city_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mOnClickListener = mOnItemClickListener;
        holder.mCity = mCities.get(position);

        holder.bind();
    }

    @Override
    public int getItemCount() {
        return mCities.size();
    }

    public void updateData(List<City> cities){
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCallback(mCities, cities));
        this.mCities = cities;
        diffResult.dispatchUpdatesTo(this);
    }

    public void clear(){
        this.mCities.clear();
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(ViewHolder.OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public City mCity;
        public OnItemClickListener mOnClickListener;

        @BindView(R.id.cities_list_item__name)
        TextView mCityName;
        @BindView(R.id.cities_list_item__country)
        TextView mCityCountry;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }

        public void bind(){
            if(mCity!=null){
                mCityName.setText(mCity.name);
                mCityCountry.setText(mCity.country);
            }

            if(mOnClickListener !=null)
                itemView.setOnClickListener(v -> mOnClickListener.onCityItemClicked(v,mCity));
        }

        public interface OnItemClickListener{
            void onCityItemClicked(View view, City city);
        }
    }

    public static class DiffCallback extends DiffUtil.Callback {

        private List<City> oldItems;
        private List<City> newItems;

        public DiffCallback(List<City> oldItems, List<City> newItems) {
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
            return oldItems.get(oldItemPosition).id == newItems.get(newItemPosition).id;
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldItems.get(oldItemPosition).equals(newItems.get(newItemPosition));
        }
    }
}
