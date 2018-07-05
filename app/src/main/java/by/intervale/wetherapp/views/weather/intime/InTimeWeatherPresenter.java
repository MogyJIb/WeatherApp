package by.intervale.wetherapp.views.weather.intime;

import java.time.LocalDateTime;

import javax.inject.Inject;

import by.intervale.wetherapp.data.api.ApiRepository;
import by.intervale.wetherapp.data.models.WeatherInTime;
import by.intervale.wetherapp.utils.ImageUtil;
import by.intervale.wetherapp.views.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class InTimeWeatherPresenter
            extends BasePresenter<IInTimeWeatherView>
            implements IInTimeWeatherPresenter{
    @Inject
    ApiRepository mRepository;

    public InTimeWeatherPresenter(ApiRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public void loadWeatherData(long cityId) {
        mDisposables.add(
                mRepository.weatherApi().weatherInTime(cityId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::handleWeatherResult, this::handleError)
        );
    }

    private void handleWeatherResult(WeatherInTime weatherInTime) {
        mView.setTemperature(weatherInTime.weatherDetails.temperature);
        mView.setTitle(weatherInTime.cityName);
        mView.setDate(String.valueOf(weatherInTime.dateTime));
        mView.setType(weatherInTime.weather.get(0).weatherType);
        mView.setDescription(weatherInTime.weather.get(0).description);
        mView.setHumidity(weatherInTime.weatherDetails.humidity);
        mView.setPressure(weatherInTime.weatherDetails.pressure);
        if (weatherInTime.rain != null)
            mView.setRain(weatherInTime.rain.rainVolume);
        if (weatherInTime.wind != null) {
            mView.setWindDirection(weatherInTime.wind.directionInDegrees);
            mView.setWindSpeed(weatherInTime.wind.speed);
        }
        if (weatherInTime.snow != null)
            mView.setSnow(weatherInTime.snow.snowVolume);
        if (weatherInTime.clouds != null)
            mView.setClouds(weatherInTime.clouds.cloudinessPercent);

        mDisposables.add(
                ImageUtil.loadWeatherImage(
                        weatherInTime.weather.get(0).icon)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(mView::setIcon, this::handleError)
        );
    }
}
