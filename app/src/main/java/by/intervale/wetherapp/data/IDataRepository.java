package by.intervale.wetherapp.data;

public interface IDataRepository {
    ICityDao cityDao();
    IFavouriteDao favouriteDao();
    boolean isInitialized();
}
