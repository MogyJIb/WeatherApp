package by.intervale.wetherapp.data.models;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import io.realm.RealmModel;
import io.realm.annotations.RealmClass;

@Entity(tableName = "city")
public class City implements Comparable<City> {

    @PrimaryKey
    public long id;
    public String name;
    public String country;

    @Embedded
    @SerializedName("coord")
    public Geometry geometry;

    @Ignore
    public City() {
    }

    public City(long id, String name, String country, Geometry geometry) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.geometry = geometry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id == city.id &&
                Objects.equals(name, city.name) &&
                Objects.equals(country, city.country) &&
                Objects.equals(geometry, city.geometry);
    }

    @Override
    public int compareTo(@NonNull City city) {
        return Long.compare(id,city.id);
    }

    /**
     * Class for data city's location geometry
     */
    public static class Geometry {

        @SerializedName("lon")
        public double longitude;

        @SerializedName("lat")
        public double latitude;

        @Ignore
        public Geometry() {
        }

        public Geometry(double longitude, double latitude) {
            this.longitude = longitude;
            this.latitude = latitude;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Geometry geometry = (Geometry) o;
            return Double.compare(geometry.longitude, longitude) == 0 &&
                    Double.compare(geometry.latitude, latitude) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(longitude, latitude);
        }
    }
}
