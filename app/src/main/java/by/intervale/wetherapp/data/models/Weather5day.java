
package by.intervale.wetherapp.data.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;

import java.util.List;
import java.util.Objects;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "weather_5day")
public class Weather5day {

    @SerializedName("code")
    public int responseCode;

    public double message;

    @Ignore
    public City city;

    @Ignore
    @SerializedName("list")
    public List<WeatherInTime> watherInTimes;

    @Ignore
    public Weather5day() {
    }

    public Weather5day(int responseCode, double message, City city,
                       List<WeatherInTime> weatherInTimes) {
        this.responseCode = responseCode;
        this.message = message;
        this.city = city;
        watherInTimes = weatherInTimes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weather5day that = (Weather5day) o;
        return responseCode == that.responseCode &&
                Objects.equals(message, that.message) &&
                Objects.equals(city, that.city) &&
                Objects.equals(watherInTimes, that.watherInTimes);
    }

    @Override
    public int hashCode() {

        return Objects.hash(responseCode, message, city, watherInTimes);
    }
}
