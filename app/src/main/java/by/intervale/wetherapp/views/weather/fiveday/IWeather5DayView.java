package by.intervale.wetherapp.views.weather.fiveday;

import java.util.List;

import by.intervale.wetherapp.data.models.WeatherInTime;
import by.intervale.wetherapp.views.base.IBaseView;

public interface IWeather5DayView
            extends IBaseView{

    void setTitle(String title);
    void updateListData(List<WeatherInTime> cities);
}
