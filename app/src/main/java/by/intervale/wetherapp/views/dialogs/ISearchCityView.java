package by.intervale.wetherapp.views.dialogs;

import java.util.List;

import by.intervale.wetherapp.models.City;
import by.intervale.wetherapp.views.base.IBaseView;

public interface ISearchCityView extends IBaseView{
    void updateData(List<City> cities);
    void clearData();
}
