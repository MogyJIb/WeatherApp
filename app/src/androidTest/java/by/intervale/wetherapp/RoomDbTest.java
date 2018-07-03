package by.intervale.wetherapp;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import by.intervale.wetherapp.data.models.City;
import by.intervale.wetherapp.data.models.Favourite;
import by.intervale.wetherapp.data.models.Geometry;
import by.intervale.wetherapp.data.room.RoomDataRepository;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class RoomDbTest {
    private RoomDataRepository.WeatherDatabase mDb;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, RoomDataRepository.WeatherDatabase.class).build();
    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }

    @Test
    public void writeCityAndReadById() throws Exception {
        City city = new City(1,"name","country",new Geometry(1,1));
        mDb.cityDao().insert(city);
        City byId = mDb.cityDao().getById(1);
        assertThat(city, equalTo(byId));
    }

    @Test
    public void writeFavouritesAndReadInList() throws Exception {
        Favourite favourite = new Favourite(1);
        List<Favourite> favourites = new ArrayList<>();
        favourites.add(favourite);
        favourites.add(new Favourite(2));

        mDb.favouriteDao().insertAll(favourites);

        favourites.remove(favourite);
        mDb.favouriteDao().delete(favourite);

        assertThat(favourites, is(mDb.favouriteDao().favourites()));
    }

    @Test
    public void readFavouritesCityById() throws Exception {
        List<City> cities = new ArrayList<>();
        cities.add(new City(1,"1","2",new Geometry(1,1)));
        cities.add(new City(2,"1","2",new Geometry(1,1)));
        cities.add(new City(3,"1","2",new Geometry(1,1)));
        cities.add(new City(4,"1","2",new Geometry(1,1)));

        mDb.cityDao().insertAll(cities);

        assertThat(cities.subList(0,2), is(mDb.cityDao().getById(Arrays.asList(1L,2L))));
    }

    @Test
    public void readFavouritesCityInList() throws Exception {
        List<City> cities = new ArrayList<>();
        cities.add(new City(1,"1","2",new Geometry(1,1)));
        cities.add(new City(2,"1","2",new Geometry(1,1)));
        cities.add(new City(3,"1","2",new Geometry(1,1)));
        cities.add(new City(4,"1","2",new Geometry(1,1)));

        mDb.cityDao().insertAll(cities);
        mDb.favouriteDao().insertAll(Arrays.asList(new Favourite(1),new Favourite(2)));

        assertThat(cities.subList(0,2), is(mDb.cityDao().favourites()));
    }
}
