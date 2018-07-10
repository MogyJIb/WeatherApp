package by.intervale.wetherapp.dagger.modules;


import by.intervale.wetherapp.data.IDataRepository;
import by.intervale.wetherapp.data.api.ApiRepository;
import by.intervale.wetherapp.router.IRouter;
import by.intervale.wetherapp.views.cities.CityPresenter;
import by.intervale.wetherapp.views.cities.ICityPresenter;
import by.intervale.wetherapp.views.search.ISearchCityPresenter;
import by.intervale.wetherapp.views.search.SearchCityPresenter;
import by.intervale.wetherapp.views.weather.detail.IWeatherDetailPresenter;
import by.intervale.wetherapp.views.weather.detail.WeatherDetailPresenter;
import by.intervale.wetherapp.views.weather.fiveday.IWeather5DayPresenter;
import by.intervale.wetherapp.views.weather.fiveday.Weather5DayPresenter;
import by.intervale.wetherapp.views.weather.intime.IWeatherInTimePresenter;
import by.intervale.wetherapp.views.weather.intime.WeatherInTimePresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    ICityPresenter provideCityPresenter(IDataRepository repository, IRouter router) {
        return new CityPresenter(repository, router);
    }

    @Provides
    ISearchCityPresenter provideSearchCityPresenter(IDataRepository repository) {
        return new SearchCityPresenter(repository);
    }

    @Provides
    IWeatherInTimePresenter provideWeatherInTimePresenter(ApiRepository repository) {
        return new WeatherInTimePresenter(repository);
    }

    @Provides
    IWeather5DayPresenter provideWeather5DayPresenter(ApiRepository repository) {
        return new Weather5DayPresenter(repository);
    }

    @Provides
    IWeatherDetailPresenter provideIWeatherDetailPresenter(IRouter router) {
        return new WeatherDetailPresenter(router);
    }
}
