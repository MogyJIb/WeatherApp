package by.intervale.wetherapp.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.concurrent.Callable;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;

public class JsonConverterUtils {
    public static  <T> T parseData(InputStreamReader inputStream, Type type){
       return new GsonBuilder().create().fromJson(inputStream,type);
    }
}
