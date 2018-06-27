package by.intervale.wetherapp.dagger.modules;

import android.content.Context;


import javax.inject.Singleton;

import by.intervale.wetherapp.Application;
import dagger.Module;
import dagger.Provides;

/**
 * Created by emoroz on 20.02.2017.
 */

@Module
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    Context provideApplicationContext() {
        return application;
    }
}
