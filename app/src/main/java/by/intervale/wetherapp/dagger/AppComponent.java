package by.intervale.wetherapp.dagger;

import javax.inject.Singleton;

import by.intervale.wetherapp.Application;
import by.intervale.wetherapp.dagger.modules.AppModule;
import by.intervale.wetherapp.dagger.modules.LogicModule;
import by.intervale.wetherapp.dagger.modules.PresenterModule;
import by.intervale.wetherapp.views.MainActivity;
import by.intervale.wetherapp.views.base.BaseActivity;
import by.intervale.wetherapp.views.base.BaseFragment;
import by.intervale.wetherapp.views.cities.CityFragment;
import by.intervale.wetherapp.views.search.SearchCityDialogFragment;
import by.intervale.wetherapp.views.weather.intime.InTimeWeatherFragment;
import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        LogicModule.class,
        PresenterModule.class})
public interface AppComponent {

    void inject(Application application);

    void inject(BaseActivity baseActivity);
    void inject(BaseFragment baseFragment);

    void inject(MainActivity mainActivity);

    void inject(CityFragment cityFragment);
    void inject(SearchCityDialogFragment searchCityDialogFragment);
    void inject(InTimeWeatherFragment inTimeWeatherFragment);
}
