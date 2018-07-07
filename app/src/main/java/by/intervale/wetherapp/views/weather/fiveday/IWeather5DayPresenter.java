package by.intervale.wetherapp.views.weather.fiveday;

import by.intervale.wetherapp.views.base.BasePresenter;
import by.intervale.wetherapp.views.base.IBasePresenter;

public interface IWeather5DayPresenter
            extends IBasePresenter<IWeather5DayView>{
    void loadWeatherData(long cityId);
}
