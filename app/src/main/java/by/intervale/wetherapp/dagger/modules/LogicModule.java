package by.intervale.wetherapp.dagger.modules;

import android.content.Context;

import javax.inject.Singleton;

import by.intervale.wetherapp.data.IDataRepository;
import by.intervale.wetherapp.data.api.ApiRepository;
import by.intervale.wetherapp.data.RoomDataRepository;
import dagger.Module;
import dagger.Provides;

@Module
public class LogicModule {
    @Singleton
    @Provides
    IDataRepository provideDataRepository(Context applicationContext){
        return new RoomDataRepository(applicationContext);
    }

    @Singleton
    @Provides
    ApiRepository provideApiRepository(){
        return new ApiRepository();
    }
}
