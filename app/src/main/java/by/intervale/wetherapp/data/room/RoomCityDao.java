package by.intervale.wetherapp.data.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import by.intervale.wetherapp.data.ICityDao;
import by.intervale.wetherapp.data.models.City;
import io.reactivex.Flowable;
import io.reactivex.Observable;

@Dao
public interface RoomCityDao extends ICityDao {
    @Query("SELECT * FROM city")
    List<City> cities();

    @Query("SELECT * FROM city WHERE name LIKE :namePattern || '%'")
    List<City> filter(String namePattern);

    @Insert
    void insert(City city);

    @Insert
    void insertAll(List<City> cities);

    @Query("SELECT COUNT(id) FROM city")
    long count();

    @Query("SELECT * FROM city WHERE id = :id")
    City getById(long id);

    @Query("SELECT * FROM city WHERE id  IN (:IDs)")
    List<City> getById(List<Long> IDs);

    @Query("SELECT * FROM city WHERE id  IN (SELECT id FROM favourite)")
    List<City> favourites();
}
