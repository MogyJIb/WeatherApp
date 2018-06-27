package by.intervale.wetherapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import io.realm.RealmObject;

public class Geometry extends RealmObject{

    @SerializedName("lon")
    public double longitude;

    @SerializedName("lat")
    public double latitude;

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


    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
