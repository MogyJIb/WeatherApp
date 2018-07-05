package by.intervale.wetherapp.utils;

import android.graphics.Bitmap;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import io.reactivex.Flowable;
import io.reactivex.Observable;


public class ImageUtil {
    public static Observable<Bitmap> loadWeatherImage(String imageName) {
        return Observable.fromCallable(() -> Picasso.get()
                .load("http://openweathermap.org/img/w/" + imageName + ".png").get());
    }
}
