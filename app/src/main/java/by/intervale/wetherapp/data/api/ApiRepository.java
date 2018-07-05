package by.intervale.wetherapp.data.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import by.intervale.wetherapp.data.models.Weather5day;
import by.intervale.wetherapp.data.models.WeatherInTime;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiRepository{
    private final static String baseUrl = "http://api.openweathermap.org/data/2.5/";
    private final Retrofit retrofit;

    public ApiRepository() {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    public WeatherApi weatherApi() {
        return retrofit.create(WeatherApi.class);
    }


    /**
     * Weather api remote methods
     */
    public interface WeatherApi {
        @GET("weather?APPID=b3404dadb531be0454a846321b38f7f0&units=metric")
        Observable<WeatherInTime> weatherInTime(@Query("id") long cityId);

        @GET("forecast?APPID=b3404dadb531be0454a846321b38f7f0&units=metric")
        Observable<Weather5day> weather5day(@Query("id") long cityId);
    }
}
