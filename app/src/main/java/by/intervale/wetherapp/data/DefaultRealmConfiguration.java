package by.intervale.wetherapp.data;

import android.support.annotation.NonNull;

import java.io.InputStream;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;

public class DefaultRealmConfiguration {
    private static final String DB_NAME = "weather-app-db";
    private static int schemaVersion = 1;

    public static RealmConfiguration getInstance() {
        return new RealmConfiguration.Builder()
                .name(DB_NAME)
                .schemaVersion(schemaVersion)
                .build();
    }


    public static class DefaultMigration implements RealmMigration {

        @Override
        public void migrate(@NonNull DynamicRealm realm, long oldVersion, long newVersion) {

        }
    }
}

