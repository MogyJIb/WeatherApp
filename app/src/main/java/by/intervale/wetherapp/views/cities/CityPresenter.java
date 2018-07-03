package by.intervale.wetherapp.views.cities;

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

public class CityPresenter
        extends BasePresenter<ICityView>
        implements ICityPresenter{

    @Inject
    IDataRepository mRepository;

    public CityPresenter(IDataRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public void loadCities() {
        mDisposables.add(
                Flowable.fromCallable(()->mRepository.cityDao().favourites())
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

    @Override
    public void addToFavourite(City city) {
        mDisposables.add(
                Flowable.fromCallable(() -> {
                            mRepository.favouriteDao().insert(new Favourite(city.id));
                            return city;
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(c -> loadCities(), this::handleError)
        );
    }
}
