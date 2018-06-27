package by.intervale.wetherapp.dagger.modules;


import by.intervale.wetherapp.logic.ICitiesDao;
import by.intervale.wetherapp.logic.RealmCitiesDao;
import by.intervale.wetherapp.views.cities.CityPresenter;
import by.intervale.wetherapp.views.cities.ICityPresenter;
import by.intervale.wetherapp.views.dialogs.ISearchCItyPresenter;
import by.intervale.wetherapp.views.dialogs.SearchCityDialogPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    ICityPresenter provideCityPresenter(ICitiesDao citiesDao) {
        return new CityPresenter(citiesDao);
    }

    @Provides
    ISearchCItyPresenter provideSearchCItyPresenter(ICitiesDao citiesDao) {
        return new SearchCityDialogPresenter(citiesDao);
    }
}
