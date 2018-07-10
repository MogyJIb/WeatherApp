package by.intervale.wetherapp.dagger.modules;

import android.content.Context;


import javax.inject.Singleton;

import by.intervale.wetherapp.Application;
import by.intervale.wetherapp.router.IRouter;
import by.intervale.wetherapp.router.AppRouter;
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

    @Singleton
    @Provides
    IRouter provideMainRouter() { return new AppRouter(); }
}
