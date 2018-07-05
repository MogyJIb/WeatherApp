package by.intervale.wetherapp.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import java.util.List;

import by.intervale.wetherapp.data.IDataRepository;
import by.intervale.wetherapp.data.models.City;
import by.intervale.wetherapp.data.models.Favourite;

public class RoomDataRepository implements IDataRepository {
    private static final String databaseName = "room-weather-db";
    private final WeatherDatabase weatherDatabase;

    public RoomDataRepository(Context context) {
        weatherDatabase =
                Room.databaseBuilder(context, WeatherDatabase.class, databaseName).build();
    }

    @Override
    public ICityDao cityDao() {
        return weatherDatabase.cityDao();
    }

    @Override
    public IFavouriteDao favouriteDao() {
        return weatherDatabase.favouriteDao();
    }

    @Override
    public boolean isInitialized() {
        return weatherDatabase.cityDao().count() != 0;
    }



    /**
     * Room database implementing
     */
    @Database(entities = {City.class, Favourite.class}, version = 3, exportSchema = false)
    public static abstract class WeatherDatabase extends RoomDatabase {
        public abstract RoomCityDao cityDao();
        public abstract RoomFavouriteDao favouriteDao();
    }

    /**
     * Room city dao
     */
    @Dao
    public interface RoomCityDao extends IDataRepository.ICityDao {
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

    /**
     * Room favourites city dao
     */
    @Dao
    public interface RoomFavouriteDao extends IDataRepository.IFavouriteDao {
        @Query("SELECT * FROM favourite")
        List<Favourite> favourites();

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insert(Favourite favourite);

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertAll(List<Favourite> favourites);

        @Delete
        void delete(Favourite favourite);
    }
}
