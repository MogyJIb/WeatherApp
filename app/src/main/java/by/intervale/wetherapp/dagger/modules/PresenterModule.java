package by.intervale.wetherapp.dagger.modules;


import javax.inject.Singleton;

import by.intervale.wetherapp.data.IDataRepository;
import by.intervale.wetherapp.views.cities.CityPresenter;
import by.intervale.wetherapp.views.cities.ICityPresenter;
import by.intervale.wetherapp.views.search.ISearchCityPresenter;
import by.intervale.wetherapp.views.search.SearchCityDialogPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    ICityPresenter provideCityPresenter(IDataRepository repository) {
        return new CityPresenter(repository);
    }

    @Provides
    ISearchCityPresenter provideSearchCityPresenter(IDataRepository repository) {
        return new SearchCityDialogPresenter(repository);
    }
}
