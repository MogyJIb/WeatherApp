package by.intervale.wetherapp.views.cities;

import android.view.View;

import by.intervale.wetherapp.data.models.City;
import by.intervale.wetherapp.views.base.IBasePresenter;

public interface ICityPresenter extends IBasePresenter<ICityView> {
    void loadCities();
    void addToFavourite(City city);
    void onAddButtonClick();
    void onItemClick(City city, View view);
    void deleteFromFavourite(City city);
}
