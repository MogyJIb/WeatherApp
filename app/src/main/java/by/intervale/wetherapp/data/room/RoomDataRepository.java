package by.intervale.wetherapp.data.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

import by.intervale.wetherapp.data.ICityDao;
import by.intervale.wetherapp.data.IDataRepository;
import by.intervale.wetherapp.data.IFavouriteDao;
import by.intervale.wetherapp.data.models.City;
import by.intervale.wetherapp.data.models.Favourite;

public class RoomDataRepository implements IDataRepository {
    private static final String databaseName = "room-weather-db";
    private final WeatherDatabase weatherDatabase;

    public RoomDataRepository(Context context) {
        weatherDatabase =
                Room.databaseBuilder(context, WeatherDatabase.class, databaseName).build();
    }

    @Database(entities = {City.class, Favourite.class}, version = 3, exportSchema = false)
    public static abstract class WeatherDatabase extends RoomDatabase {
        public abstract RoomCityDao cityDao();
        public abstract RoomFavouriteDao favouriteDao();
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
}
