package by.intervale.wetherapp.views.cities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import butterknife.BindView;
import by.intervale.wetherapp.R;
import by.intervale.wetherapp.data.models.City;
import by.intervale.wetherapp.views.base.BaseRecyclerViewAdapter;


public class CityRecyclerViewAdapter extends BaseRecyclerViewAdapter<City, CityRecyclerViewAdapter.CityViewHolder> {

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_city_list_item, parent, false);
        return new CityViewHolder(view);
    }

    public static class CityViewHolder extends BaseRecyclerViewAdapter.BaseViewHolder<City> {

        @BindView(R.id.cities_list_item__name)
        TextView mCityName;
        @BindView(R.id.cities_list_item__country)
        TextView mCityCountry;

        public CityViewHolder(View view) {
            super(view);
        }

        public void bind(){
            if(mItem!=null){
                mCityName.setText(mItem.name);
                mCityCountry.setText(mItem.country);
            }

            if(mOnClickListener !=null)
                itemView.setOnClickListener(v -> mOnClickListener.onItemClicked(mItem));
        }
    }
}
