package by.intervale.wetherapp.views.cities;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import javax.inject.Inject;

import by.intervale.wetherapp.data.IDataRepository;
import by.intervale.wetherapp.data.models.City;
import by.intervale.wetherapp.data.models.Favourite;
import by.intervale.wetherapp.router.IRouter;
import by.intervale.wetherapp.router.Screens;
import by.intervale.wetherapp.views.base.BasePresenter;
import by.intervale.wetherapp.views.search.SearchCityDialogFragment;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CityPresenter
        extends BasePresenter<ICityView>
        implements ICityPresenter{

    IRouter mRouter;
    IDataRepository mRepository;


    @Inject
    public CityPresenter(IDataRepository repository, IRouter router) {
        this.mRepository = repository;
        this.mRouter = router;
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

    @Override
    public void deleteFromFavourite(City city) {
        mDisposables.add(
                Flowable.fromCallable(() -> {
                            mRepository.favouriteDao().delete(new Favourite(city.id));
                            return city;
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(c -> loadCities(), this::handleError)
        );
    }

    @Override
    public void onAddButtonClick() {
        mRouter.showSystemDialog(
                new SearchCityDialogFragment().setOnSearchResultListener(this::addToFavourite)
        );
    }

    @Override
    public void onItemClick(City city, View view) {
        mRouter.navigateTo(Screens.WEATHER_DETAIL, city.id);
    }
}
