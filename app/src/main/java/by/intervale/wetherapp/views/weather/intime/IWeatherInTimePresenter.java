package by.intervale.wetherapp.views.weather.intime;

import by.intervale.wetherapp.views.base.IBasePresenter;

public interface IWeatherInTimePresenter
        extends IBasePresenter<IWeatherInTimeView> {
    void loadWeatherData(long cityId);
}
