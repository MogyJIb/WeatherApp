package by.intervale.wetherapp.data.models;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;
import android.support.annotation.NonNull;

import java.util.List;
import java.util.Objects;

import io.realm.annotations.RealmClass;

@Entity(tableName = "favourite")
public class Favourite {
    @PrimaryKey
    public long id;

    @Ignore
    public Favourite() {
    }

    public Favourite(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favourite favourite = (Favourite) o;
        return id == favourite.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Favourite{" +
                "id=" + id +
                '}';
    }
}
