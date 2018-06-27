package by.intervale.wetherapp.views.dialogs;

import java.util.List;

import by.intervale.wetherapp.models.City;
import by.intervale.wetherapp.views.base.IBasePresenter;

public interface ISearchCItyPresenter extends IBasePresenter<ISearchCityView>{
    void searchCities(String pattern);
}
