package by.intervale.wetherapp.views.weather.fiveday;

import android.view.View;

import by.intervale.wetherapp.data.models.WeatherInTime;
import by.intervale.wetherapp.views.base.BasePresenter;
import by.intervale.wetherapp.views.base.IBasePresenter;

public interface IWeather5DayPresenter
            extends IBasePresenter<IWeather5DayView>{
    void loadWeatherData(long cityId);
    void onItemClick(WeatherInTime weatherInTime, View view);
}
