package by.intervale.wetherapp.data;

import java.util.List;

import by.intervale.wetherapp.data.models.City;
import by.intervale.wetherapp.data.models.Favourite;

public interface IDataRepository {
    ICityDao cityDao();
    IFavouriteDao favouriteDao();
    boolean isInitialized();



    /**
     * Favourite city data DAO class
     */
    interface IFavouriteDao {
        void insert(Favourite favourite);
        void insertAll(List<Favourite> favourites);
        void delete(Favourite favourite);
        List<Favourite> favourites();
    }

    /**
     * City data DAO class
     */
    interface ICityDao {
        List<City> cities();
        List<City> filter(String namePattern);
        long count();
        void insert(City city);
        void insertAll(List<City> cities);
        City getById(long id);
        List<City> getById(List<Long> IDs);
        List<City> favourites();
    }

    /**
     * Weather data DAO class
     */
    interface IWeatherDao {

    }
}


