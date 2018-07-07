package by.intervale.wetherapp.views.weather;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.intervale.wetherapp.Application;
import by.intervale.wetherapp.R;
import by.intervale.wetherapp.views.base.BaseActivity;
import by.intervale.wetherapp.views.base.ViewPagerAdapter;
import by.intervale.wetherapp.views.weather.fiveday.Weather5DayFragment;
import by.intervale.wetherapp.views.weather.intime.WeatherInTimeFragment;

public class WeatherDetailActivity
        extends BaseActivity {

    @BindView(R.id.fr_weather_detail__toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fr_weather_detail__tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.fr_weather_detail__viewpager)
    ViewPager mViewPager;

    public static final String CITY_ID = "cityId";
    private long cityId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);
        ButterKnife.bind(this);

        if (getIntent() != null)
            cityId = getIntent().getLongExtra(CITY_ID, 0);
        else return;

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewPager.setAdapter(
                new ViewPagerAdapter(getSupportFragmentManager())
                        .addFragment(new WeatherInTimeFragment().setCityId(cityId), "Current")
                        .addFragment(new Weather5DayFragment().setCityId(cityId), "5Day")
        );

        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected @IdRes
    int getContainerId() {
        return R.id.act_weather_detail__container;
    }
}
