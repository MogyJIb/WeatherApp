package by.intervale.wetherapp.views.weather.detail;

import javax.inject.Inject;

import by.intervale.wetherapp.data.IDataRepository;
import by.intervale.wetherapp.router.IRouter;
import by.intervale.wetherapp.views.base.BasePresenter;

public class WeatherDetailPresenter
        extends BasePresenter<IWeatherDetailView>
        implements IWeatherDetailPresenter {

    IRouter mRouter;


    @Inject
    public WeatherDetailPresenter(IRouter router) {
        this.mRouter = router;
    }
}
