package by.intervale.wetherapp.views.weather.fiveday;

import android.view.View;

import javax.inject.Inject;

import by.intervale.wetherapp.data.api.ApiRepository;
import by.intervale.wetherapp.data.models.Weather5day;
import by.intervale.wetherapp.data.models.WeatherInTime;
import by.intervale.wetherapp.views.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Weather5DayPresenter
        extends BasePresenter<IWeather5DayView>
        implements IWeather5DayPresenter {

    @Inject
    ApiRepository mRepository;

    public Weather5DayPresenter(ApiRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public void loadWeatherData(long cityId) {
        mDisposables.add(
                mRepository.weatherApi().weather5day(cityId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::handleWeatherResult, this::handleError)
        );
    }

    private void handleWeatherResult(Weather5day weather5day) {
        mView.setTitle(weather5day.city.name);
        mView.updateListData(weather5day.watherInTimes);
    }

    @Override
    public void onItemClick(WeatherInTime weatherInTime, View view) {

    }
}
