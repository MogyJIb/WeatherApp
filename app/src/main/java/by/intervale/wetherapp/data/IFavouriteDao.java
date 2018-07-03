package by.intervale.wetherapp.data;

import java.util.List;

import by.intervale.wetherapp.data.models.Favourite;

public interface IFavouriteDao {
    void insert(Favourite favourite);
    void insertAll(List<Favourite> favourites);
    void delete(Favourite favourite);
    List<Favourite> favourites();
}
