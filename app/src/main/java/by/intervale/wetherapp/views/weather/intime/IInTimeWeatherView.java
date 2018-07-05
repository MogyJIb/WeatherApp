package by.intervale.wetherapp.views.weather.intime;

import android.graphics.Bitmap;

import java.time.LocalDateTime;

import by.intervale.wetherapp.views.base.IBaseView;

public interface IInTimeWeatherView extends IBaseView {
    void setTitle(String title);
    void setDate(String date);
    void setTemperature(double temperature);
    void setType(String type);
    void setDescription(String description);
    void setHumidity(int humidity);
    void setPressure(double pressure);
    void setWindDirection(int windDirection);
    void setWindSpeed(double windSpeed);
    void setRain(int rain);
    void setClouds(int clouds);
    void setSnow(int snow);
    void setIcon(Bitmap bitmap);
}

