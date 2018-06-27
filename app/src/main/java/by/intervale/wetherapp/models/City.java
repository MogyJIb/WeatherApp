package by.intervale.wetherapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class City extends RealmObject {
    @PrimaryKey
    public long id;
    public String name;
    public String country;

    @SerializedName("coord")
    public Geometry geometry;

    public City(){}

    public City(long id, String name, String country, Geometry geometry) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.geometry = geometry;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", geometry=" + geometry +
                '}';
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
    public int hashCode() {
        return Objects.hash(id, name, country, geometry);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}
