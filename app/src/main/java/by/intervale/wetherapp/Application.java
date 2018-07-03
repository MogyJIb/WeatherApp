package by.intervale.wetherapp;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import by.intervale.wetherapp.dagger.AppComponent;
import by.intervale.wetherapp.dagger.DaggerAppComponent;
import by.intervale.wetherapp.dagger.modules.AppModule;
import by.intervale.wetherapp.data.IDataRepository;
import by.intervale.wetherapp.data.models.City;
import by.intervale.wetherapp.utils.JsonConverterUtils;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Application extends android.app.Application{

    @Inject
    IDataRepository mRepository;

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

        initAppComponent();
        initDatabase();
    }

    @NonNull
    public static AppComponent applicationComponent() {
        return appComponent;
    }

    private void initAppComponent(){
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);
    }

    private void initDatabase() {
        Observable.fromCallable(() -> mRepository.isInitialized())
                .flatMap(isInitialized -> isInitialized
                        ? Observable.empty()
                        : Observable.fromCallable((Callable<List<City>>) () -> JsonConverterUtils.parseData(
                            new InputStreamReader(getResources().openRawResource(R.raw.cities_list)),
                            new TypeToken<List<City>>() {}.getType())
                ))
                .flatMap(cities -> Observable.fromCallable(() -> {
                    mRepository.cityDao().insertAll(cities);
                    return true;
                }))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}