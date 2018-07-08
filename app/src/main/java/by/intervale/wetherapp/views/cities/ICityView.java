package by.intervale.wetherapp.views.cities;


import android.view.ActionMode;

import java.util.List;
import by.intervale.wetherapp.data.models.City;
import by.intervale.wetherapp.views.base.IBaseView;

public interface ICityView extends IBaseView{
    void updateData(List<City> cities);
}
