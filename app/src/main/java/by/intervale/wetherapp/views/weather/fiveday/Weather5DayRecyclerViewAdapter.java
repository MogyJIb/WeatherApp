package by.intervale.wetherapp.views.weather.fiveday;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import by.intervale.wetherapp.R;
import by.intervale.wetherapp.data.models.WeatherInTime;
import by.intervale.wetherapp.utils.ImageUtil;
import by.intervale.wetherapp.views.base.BaseRecyclerViewAdapter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Weather5DayRecyclerViewAdapter
        extends BaseRecyclerViewAdapter<WeatherInTime, Weather5DayRecyclerViewAdapter.WeatherViewHolder> {

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_5day_weather_list_item, parent, false);
        return new WeatherViewHolder(view);
    }

    public static class WeatherViewHolder extends BaseRecyclerViewAdapter.BaseViewHolder<WeatherInTime> {

        @BindView(R.id.fr_weather_5day__temp)
        TextView mTemperature;
        @BindView(R.id.fr_weather_5day__type)
        TextView mType;
        @BindView(R.id.fr_weather_5day__description)
        TextView mDescription;
        @BindView(R.id.fr_weather_5day__icon)
        ImageView mWeatherImageView;

        public WeatherViewHolder(View view) {
            super(view);
        }

        @Override
        public void bind() {
            super.bind();
            if (mItem == null)
                return;

            mTemperature.setText(String.valueOf(mItem.weatherDetails.temperature));
            mType.setText(mItem.weather.get(0).weatherType);
            mDescription.setText(mItem.weather.get(0).description);
            mDisposables.add(
                    ImageUtil.loadWeatherImage(
                            mItem.weather.get(0).icon)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(mWeatherImageView::setImageBitmap, Throwable::printStackTrace)
            );
        }
    }
}
