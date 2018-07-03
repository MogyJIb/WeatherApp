package by.intervale.wetherapp.views.cities;

import by.intervale.wetherapp.data.models.City;
import by.intervale.wetherapp.views.base.IBasePresenter;

public interface ICityPresenter extends IBasePresenter<ICityView> {
    void loadCities();
    void addToFavourite(City city);
}
