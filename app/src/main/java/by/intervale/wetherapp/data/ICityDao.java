package by.intervale.wetherapp.data;

import java.util.List;

import by.intervale.wetherapp.data.models.City;
import io.reactivex.Flowable;

public interface ICityDao {
    List<City> cities();
    List<City> filter(String namePattern);
    long count();
    void insert(City city);
    void insertAll(List<City> cities);
    City getById(long id);
    List<City> getById(List<Long> IDs);
    List<City> favourites();
}
