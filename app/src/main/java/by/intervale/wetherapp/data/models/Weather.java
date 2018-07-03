package by.intervale.wetherapp.data.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Ignore;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Weather {

    @ColumnInfo(name = "weather_id")
    public int id;

    @SerializedName("main")
    public String weatherType;

    public String description;
    public String icon;

    @Ignore
    public Weather() {
    }

    public Weather(int id, String weatherType, String description, String icon) {
        this.id = id;
        this.weatherType = weatherType;
        this.description = description;
        this.icon = icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weather weather = (Weather) o;
        return id == weather.id &&
                Objects.equals(weatherType, weather.weatherType) &&
                Objects.equals(description, weather.description) &&
                Objects.equals(icon, weather.icon);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, weatherType, description, icon);
    }

    /**
     * Class for data weather's clouds
     */
    public static class Clouds {

        @SerializedName("all")
        private int cloudinessPercent;

        @Ignore
        public Clouds() {
        }

        public Clouds(int cloudinessPercent) {
            this.cloudinessPercent = cloudinessPercent;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Clouds clouds = (Clouds) o;
            return cloudinessPercent == clouds.cloudinessPercent;
        }

        @Override
        public int hashCode() {

            return Objects.hash(cloudinessPercent);
        }
    }

    /**
     * Class for data weather's rain
     */
    public static class Rain {

        @SerializedName("3h")
        private int rainVolume;

        @Ignore
        public Rain() {
        }

        public Rain(int rainVolume) {
            this.rainVolume = rainVolume;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Rain rain = (Rain) o;
            return rainVolume == rain.rainVolume;
        }

        @Override
        public int hashCode() {

            return Objects.hash(rainVolume);
        }
    }

    /**
     * Class for data weather's snow
     */
    public static class Snow {

        @SerializedName("3h")
        private int snowVolume;

        @Ignore
        public Snow() {
        }

        public Snow(int snowVolume) {
            super();
            this.snowVolume = snowVolume;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Snow snow = (Snow) o;
            return snowVolume == snow.snowVolume;
        }

        @Override
        public int hashCode() {

            return Objects.hash(snowVolume);
        }
    }

    /**
     * Class for data weather's wind
     */
    public static class Wind {
        private double speed;
        @SerializedName("deg")
        private int directionInDegrees;

        @Ignore
        public Wind() {
        }

        public Wind(double speed, int directionInDegrees) {
            this.speed = speed;
            this.directionInDegrees = directionInDegrees;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Wind wind = (Wind) o;
            return Double.compare(wind.speed, speed) == 0 &&
                    directionInDegrees == wind.directionInDegrees;
        }

        @Override
        public int hashCode() {

            return Objects.hash(speed, directionInDegrees);
        }
    }

    /**
     * Class for data weather of special country, such as sunrise or sunset
     */
    public static class WeatherCountryInform {

        @ColumnInfo(name = "weather_country_id")
        public int id;
        public int type;
        public double message;
        public String country;
        public int sunrise;
        public int sunset;

        @Ignore
        public WeatherCountryInform() {
        }

        public WeatherCountryInform(int type, int id, double message, String country, int sunrise, int sunset) {
            this.type = type;
            this.id = id;
            this.message = message;
            this.country = country;
            this.sunrise = sunrise;
            this.sunset = sunset;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            WeatherCountryInform that = (WeatherCountryInform) o;
            return id == that.id &&
                    type == that.type &&
                    Double.compare(that.message, message) == 0 &&
                    sunrise == that.sunrise &&
                    sunset == that.sunset &&
                    Objects.equals(country, that.country);
        }

        @Override
        public int hashCode() {

            return Objects.hash(id, type, message, country, sunrise, sunset);
        }
    }

    /**
     * Class for data weather's details
     */
    public static class WeatherDetails {

        @SerializedName("temp")
        public double temperature;
        public double pressure;
        public int humidity;
        @SerializedName("temp_min")
        public double temperatureMin;
        @SerializedName("temp_max")
        public double temperatureMax;
        @SerializedName("sea_level")
        public double pressureSeaLevel;
        @SerializedName("grnd_level")
        public double pressureGroundLevel;

        @Ignore
        public WeatherDetails() {
        }

        public WeatherDetails(double temperature, double pressure, int humidity, double temperatureMin,
                              double temperatureMax, double pressureSeaLevel, double pressureGroundLevel) {
            super();
            this.temperature = temperature;
            this.pressure = pressure;
            this.humidity = humidity;
            this.temperatureMin = temperatureMin;
            this.temperatureMax = temperatureMax;
            this.pressureSeaLevel = pressureSeaLevel;
            this.pressureGroundLevel = pressureGroundLevel;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            WeatherDetails that = (WeatherDetails) o;
            return Double.compare(that.temperature, temperature) == 0 &&
                    Double.compare(that.pressure, pressure) == 0 &&
                    humidity == that.humidity &&
                    Double.compare(that.temperatureMin, temperatureMin) == 0 &&
                    Double.compare(that.temperatureMax, temperatureMax) == 0 &&
                    Double.compare(that.pressureSeaLevel, pressureSeaLevel) == 0 &&
                    Double.compare(that.pressureGroundLevel, pressureGroundLevel) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(temperature, pressure, humidity, temperatureMin,
                    temperatureMax, pressureSeaLevel, pressureGroundLevel);
        }
    }
}