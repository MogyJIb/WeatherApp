package by.intervale.wetherapp.views.search;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import by.intervale.wetherapp.data.IDataRepository;
import by.intervale.wetherapp.data.models.City;
import by.intervale.wetherapp.data.models.Favourite;
import by.intervale.wetherapp.views.base.BasePresenter;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchCityDialogPresenter
        extends BasePresenter<ISearchCityView>
        implements ISearchCityPresenter {

    @Inject
    IDataRepository mRepository;

    public SearchCityDialogPresenter(IDataRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public void searchCities(String pattern) {
        if(pattern.length()<3){
            mView.clearData();
            return;
        }

        mDisposables.add(
                Flowable.fromCallable(()->mRepository.cityDao().filter(pattern))
                        .flatMap(cities -> Observable.fromIterable(cities)
                                .sorted((o1, o2) -> o1.name.compareTo(o2.name))
                                .toList()
                                .toFlowable()
                        )
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(cities -> mView.updateData(cities), this::handleError)
        );
    }
}
