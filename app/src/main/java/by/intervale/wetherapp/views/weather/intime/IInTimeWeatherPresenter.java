package by.intervale.wetherapp.views.weather.intime;

import by.intervale.wetherapp.views.base.IBasePresenter;

public interface IInTimeWeatherPresenter
        extends IBasePresenter<IInTimeWeatherView> {
    void loadWeatherData(long cityId);
}
