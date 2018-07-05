package by.intervale.wetherapp.views.weather.intime;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.time.LocalDateTime;

import javax.inject.Inject;

import butterknife.BindView;
import by.intervale.wetherapp.Application;
import by.intervale.wetherapp.R;
import by.intervale.wetherapp.views.base.BaseFragment;

public class InTimeWeatherFragment
            extends BaseFragment
            implements IInTimeWeatherView{

    @BindView(R.id.fr_weather_intime__title)
    TextView mTitle;
    @BindView(R.id.fr_weather_intime__date)
    TextView mDate;
    @BindView(R.id.fr_weather_intime__temp)
    TextView mTemperature;
    @BindView(R.id.fr_weather_intime__type)
    TextView mType;
    @BindView(R.id.fr_weather_intime__description)
    TextView mDescription;
    @BindView(R.id.fr_weather_intime__humidity)
    TextView mHumidity;
    @BindView(R.id.fr_weather_intime__pressure)
    TextView mPressure;
    @BindView(R.id.fr_weather_intime__wind_dir)
    TextView mWindDirection;
    @BindView(R.id.fr_weather_intime__wind_speed)
    TextView mWindSpeed;
    @BindView(R.id.fr_weather_intime__rain)
    TextView mRain;
    @BindView(R.id.fr_weather_intime__clouds)
    TextView mClouds;
    @BindView(R.id.fr_weather_intime__snow)
    TextView mSnow;
    @BindView(R.id.fr_weather_intime__icon)
    ImageView mWeatherImageView;

    @Inject
    IInTimeWeatherPresenter mPresenter;

    private long cityId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather_intime, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Application.applicationComponent().inject(this);

        mPresenter.bindView(this);
        if(savedInstanceState==null) mPresenter.loadWeatherData(cityId);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.unbindView();
    }

    public InTimeWeatherFragment setCityId(long cityId) {
        this.cityId = cityId;
        return this;
    }

    @Override
    public void setTitle(String title) {
        mTitle.setText(title);
    }
    @Override
    public void setDate(String date) {
        mDate.setText(date);
    }
    @Override
    public void setTemperature(double temperature) {
        mTemperature.setText(String.valueOf(temperature));
    }
    @Override
    public void setType(String type) {
        mType.setText(type);
    }
    @Override
    public void setDescription(String description) {
        mDescription.setText(description);
    }
    @Override
    public void setHumidity(int humidity) {
        mHumidity.setText(String.valueOf(humidity));
    }
    @Override
    public void setPressure(double pressure) {
        mPressure.setText(String.valueOf(pressure));
    }
    @Override
    public void setWindDirection(int windDirection) {
        mWindDirection.setText(String.valueOf(windDirection));
    }
    @Override
    public void setWindSpeed(double windSpeed) {
        mWindSpeed.setText(String.valueOf(windSpeed));
    }
    @Override
    public void setRain(int rain) {
        mRain.setText(String.valueOf(rain));
    }
    @Override
    public void setClouds(int clouds) {
        mClouds.setText(String.valueOf(clouds));
    }
    @Override
    public void setSnow(int snow) {
        mSnow.setText(String.valueOf(snow));
    }
    @Override
    public void setIcon(Bitmap bitmap) {
        mWeatherImageView.setImageBitmap(bitmap);
    }
}
