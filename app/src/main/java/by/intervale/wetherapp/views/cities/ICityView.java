package by.intervale.wetherapp.views.cities;

import java.util.Collection;
import java.util.List;
import by.intervale.wetherapp.models.City;
import by.intervale.wetherapp.views.base.IBaseView;

public interface ICityView extends IBaseView{
    void updateData(List<City> cities);
}
