package by.intervale.wetherapp.views.cities;

import javax.inject.Inject;

import by.intervale.wetherapp.logic.ICitiesDao;
import by.intervale.wetherapp.views.base.BasePresenter;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class CityPresenter
        extends BasePresenter<ICityView>
        implements ICityPresenter{

    @Inject
    ICitiesDao mCitiesDao;

    public CityPresenter(ICitiesDao citiesDao) {
        mCitiesDao = citiesDao;
    }

    @Override
    public void loadCities() {
        mDisposables.add(
                mCitiesDao.getCities()
                        .flatMap(cities -> Observable.fromIterable(cities)
                                .sorted((o1, o2) -> o1.name.compareTo(o2.name))
                                .toList()
                                .toObservable()
                        )
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(cities -> mView.updateData(cities), this::handleError)
        );
    }
}
