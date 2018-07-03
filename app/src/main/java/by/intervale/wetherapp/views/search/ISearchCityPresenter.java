package by.intervale.wetherapp.views.search;

import by.intervale.wetherapp.data.models.City;
import by.intervale.wetherapp.views.base.IBasePresenter;

public interface ISearchCityPresenter extends IBasePresenter<ISearchCityView>{
    void searchCities(String pattern);
}
