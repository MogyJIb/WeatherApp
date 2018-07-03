package by.intervale.wetherapp.dagger.modules;

import android.content.Context;

import javax.inject.Singleton;

import by.intervale.wetherapp.data.IDataRepository;
import by.intervale.wetherapp.data.room.RoomDataRepository;
import dagger.Module;
import dagger.Provides;

@Module
public class LogicModule {
    @Singleton
    @Provides
    IDataRepository provideRepository(Context applicationContext){
        return new RoomDataRepository(applicationContext);
    }
}
