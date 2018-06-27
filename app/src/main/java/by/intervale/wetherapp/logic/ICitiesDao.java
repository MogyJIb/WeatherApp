package by.intervale.wetherapp.logic;

import java.util.List;

import by.intervale.wetherapp.models.City;
import io.reactivex.Flowable;
import io.reactivex.Observable;

public interface ICitiesDao {
    Observable<List<City>> getCities();
    Observable<List<City>> filterCities(String namePattern);
}
