package by.intervale.wetherapp.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

import butterknife.ButterKnife;
import by.intervale.wetherapp.Application;
import by.intervale.wetherapp.R;
import by.intervale.wetherapp.router.IRouter;
import by.intervale.wetherapp.router.MainRouter;
import by.intervale.wetherapp.router.Screens;
import by.intervale.wetherapp.views.base.BaseActivity;
import by.intervale.wetherapp.views.cities.CityFragment;
import by.intervale.wetherapp.views.search.SearchCityDialogFragment;
import by.intervale.wetherapp.views.weather.WeatherDetailFragment;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.SupportAppNavigator;

public class MainActivity extends BaseActivity {

    @Inject
    IRouter mRouter;
    private Cicerone<MainRouter> mCicerone;
    private Navigator mNavigator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Application.applicationComponent().inject(this);
        ButterKnife.bind(this);

        mNavigator = new SupportAppNavigator(this, getContainerId()) {
            @Override
            protected Intent createActivityIntent(Context context, String screenKey, Object data) {
                return null;
            }

            @Override
            protected Fragment createFragment(String screenKey, Object data) {
                if (screenKey.equals(Screens.SEARCH_CITY))
                    return new SearchCityDialogFragment()
                            .setOnSearchResultListener(
                                    (SearchCityDialogFragment.OnSearchResultListener) data
                            );

                else if (screenKey.equals(Screens.FAVOURITE_CITY_LIST))
                    return new CityFragment();

                else if (screenKey.equals(Screens.WEATHER_DETAIL))
                    return new WeatherDetailFragment()
                            .setCityId((Long) data);

                else return null;
            }
        };

        mCicerone = Cicerone.create((MainRouter) mRouter);
        mCicerone.getNavigatorHolder().setNavigator(mNavigator);

        mRouter.navigateTo(Screens.FAVOURITE_CITY_LIST);
    }

    @Override
    protected @IdRes
    int getContainerId() {
        return R.id.act_main__container;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mCicerone != null) mCicerone.getNavigatorHolder().setNavigator(mNavigator);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mCicerone != null) mCicerone.getNavigatorHolder().removeNavigator();
    }
}
