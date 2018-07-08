package by.intervale.wetherapp.router;

import by.intervale.wetherapp.views.cities.CityFragment;
import by.intervale.wetherapp.views.search.SearchCityDialogFragment;
import by.intervale.wetherapp.views.weather.WeatherDetailFragment;

public interface Screens {
    String SEARCH_CITY = SearchCityDialogFragment.class.getSimpleName();
    String FAVOURITE_CITY_LIST = CityFragment.class.getSimpleName();
    String WEATHER_DETAIL = WeatherDetailFragment.class.getSimpleName();
}
