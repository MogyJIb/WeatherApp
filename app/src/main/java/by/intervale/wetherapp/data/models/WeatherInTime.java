
package by.intervale.wetherapp.data.models;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;
import java.util.Objects;

import com.google.gson.annotations.SerializedName;

import by.intervale.wetherapp.data.models.City.Geometry;

import static by.intervale.wetherapp.data.models.Weather.*;

@Entity(tableName = "weather_in_time")
public class WeatherInTime {
    @PrimaryKey
    public int dateTime;

    @Embedded
    @SerializedName("coord")
    public Geometry geometry;

    @Ignore
    public List<Weather> weather = null;
    public String base;

    @Embedded
    @SerializedName("main")
    public WeatherDetails weatherDetails;

    public int visibility;

    @Embedded
    public Wind wind;
    @Embedded
    public Clouds clouds;
    @Embedded
    public Rain rain;
    @Embedded
    public Snow snow;

    @Embedded
    @SerializedName("sys")
    public WeatherCountryInform weatherCountryInform;

    @SerializedName("id")
    public int cityId;

    @SerializedName("name")
    public String cityName;

    @SerializedName("cod")
    public int responseCode;

    @Ignore
    public WeatherInTime() {
    }

    public WeatherInTime(Geometry geometry, List<Weather> weather, String base,
                         WeatherDetails weatherDetails, int visibility, Wind wind,
                         Clouds clouds, Rain rain, Snow snow,
                         int dateTime, WeatherCountryInform weatherCountryInform,
                         int cityId, String cityName, int responseCode) {

        this.geometry = geometry;
        this.weather = weather;
        this.base = base;
        this.weatherDetails = weatherDetails;
        this.visibility = visibility;
        this.wind = wind;
        this.clouds = clouds;
        this.rain = rain;
        this.snow = snow;
        this.dateTime = dateTime;
        this.weatherCountryInform = weatherCountryInform;
        this.cityId = cityId;
        this.cityName = cityName;
        this.responseCode = responseCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherInTime that = (WeatherInTime) o;
        return dateTime == that.dateTime &&
                visibility == that.visibility &&
                cityId == that.cityId &&
                responseCode == that.responseCode &&
                Objects.equals(geometry, that.geometry) &&
                Objects.equals(weather, that.weather) &&
                Objects.equals(base, that.base) &&
                Objects.equals(weatherDetails, that.weatherDetails) &&
                Objects.equals(wind, that.wind) &&
                Objects.equals(clouds, that.clouds) &&
                Objects.equals(rain, that.rain) &&
                Objects.equals(snow, that.snow) &&
                Objects.equals(weatherCountryInform, that.weatherCountryInform) &&
                Objects.equals(cityName, that.cityName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(dateTime, geometry, weather, base, weatherDetails, visibility, wind, clouds, rain, snow, weatherCountryInform, cityId, cityName, responseCode);
    }
}
