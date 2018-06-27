package by.intervale.wetherapp.views.dialogs;

import javax.inject.Inject;

import by.intervale.wetherapp.logic.ICitiesDao;
import by.intervale.wetherapp.views.base.BasePresenter;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class SearchCityDialogPresenter
        extends BasePresenter<ISearchCityView>
        implements ISearchCItyPresenter {

    @Inject
    ICitiesDao mCitiesDao;

    public SearchCityDialogPresenter(ICitiesDao citiesDao) {
        mCitiesDao = citiesDao;
    }

    @Override
    public void searchCities(String pattern) {
        if(pattern.length()<3){
            mView.clearData();
            return;
        }

        mDisposables.add(
                mCitiesDao.filterCities(pattern)
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
