package by.intervale.wetherapp.data.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import by.intervale.wetherapp.data.IFavouriteDao;
import by.intervale.wetherapp.data.models.Favourite;

@Dao
public interface RoomFavouriteDao extends IFavouriteDao {
    @Query("SELECT * FROM favourite")
    List<Favourite> favourites();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Favourite favourite);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Favourite> favourites);

    @Delete
    void delete(Favourite favourite);
}
