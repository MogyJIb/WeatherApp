package by.intervale.wetherapp.logic;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import by.intervale.wetherapp.R;
import by.intervale.wetherapp.models.City;
import by.intervale.wetherapp.models.Geometry;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.rx.RxObservableFactory;

public class RealmCitiesDao implements ICitiesDao {

    @Override
    public Observable<List<City>> getCities() {
        return Observable.create(emitter ->{
            Realm realm = Realm.getDefaultInstance();

            realm.executeTransactionAsync(r -> {
                        RealmResults<City> results =
                                r.where(City.class)
                                        .findAll();
                        List<City> cities = r.copyFromRealm(results);
                        emitter.onNext(cities);
                    },
                    () -> { realm.close(); emitter.onComplete();},
                    Throwable::printStackTrace
            );
        });
    }

    @Override
    public Observable<List<City>> filterCities(String namePattern) {
        return Observable.create(emitter ->{
            Realm realm = Realm.getDefaultInstance();

            realm.executeTransactionAsync(r -> {
                        RealmResults<City> results =
                                r.where(City.class)
                                        .beginsWith("name", namePattern, Case.INSENSITIVE)
                                        .findAll();
                        List<City> cities = r.copyFromRealm(results);
                        emitter.onNext(cities);
                    },
                    () -> { realm.close(); emitter.onComplete();},
                    Throwable::printStackTrace
            );
        });
    }
}
