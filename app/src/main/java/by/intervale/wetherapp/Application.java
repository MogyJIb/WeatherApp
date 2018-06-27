package by.intervale.wetherapp;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import by.intervale.wetherapp.dagger.AppComponent;
import by.intervale.wetherapp.dagger.DaggerAppComponent;
import by.intervale.wetherapp.data.DefaultRealmConfiguration;
import by.intervale.wetherapp.models.City;
import by.intervale.wetherapp.models.Geometry;
import dagger.android.DaggerApplication;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class Application extends android.app.Application{
    @NonNull
    private static AppComponent appComponent;

    @NonNull
    public static Application get(@NonNull Context context) {
        return (Application) context.getApplicationContext();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
        initAppComponent();
    }

    @NonNull
    public static AppComponent applicationComponent() {
        return appComponent;
    }

    private void initAppComponent(){
        appComponent = DaggerAppComponent.create();
    }

    private void initRealm(){
        Realm.init(this);
        Realm.setDefaultConfiguration(DefaultRealmConfiguration.getInstance());

        Realm realm = Realm.getDefaultInstance();

        realm.executeTransactionAsync(r -> {
            r.deleteAll();
            final boolean isCreated = r.where(City.class).findAll().size() != 0;

            if (isCreated)
                return;

            try {
                r.createAllFromJson(City.class, getResources().openRawResource(R.raw.cities_list));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }, realm::close, Throwable::printStackTrace);
    }
}
