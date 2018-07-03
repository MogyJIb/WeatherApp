package by.intervale.wetherapp.data.models;

import android.arch.persistence.room.Ignore;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import io.realm.RealmModel;
import io.realm.annotations.RealmClass;

public class Geometry{

    @SerializedName("lon")
    public double longitude;

    @SerializedName("lat")
    public double latitude;

    @Ignore
    public Geometry(){}

    public Geometry(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Geometry{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
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
