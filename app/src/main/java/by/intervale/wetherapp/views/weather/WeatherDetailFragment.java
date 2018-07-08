package by.intervale.wetherapp.views.weather;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.intervale.wetherapp.R;
import by.intervale.wetherapp.views.base.BaseActivity;
import by.intervale.wetherapp.views.base.BaseFragment;
import by.intervale.wetherapp.views.base.ViewPagerAdapter;
import by.intervale.wetherapp.views.weather.fiveday.Weather5DayFragment;
import by.intervale.wetherapp.views.weather.intime.WeatherInTimeFragment;

public class WeatherDetailFragment
        extends BaseFragment {

    @BindView(R.id.fr_weather_detail__tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.fr_weather_detail__viewpager)
    ViewPager mViewPager;

    private long cityId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mViewPager.setAdapter(
                new ViewPagerAdapter(getFragmentManager())
                        .addFragment(new WeatherInTimeFragment().setCityId(cityId), "Current")
                        .addFragment(new Weather5DayFragment().setCityId(cityId), "5Day")
        );

        mTabLayout.setupWithViewPager(mViewPager);
    }

    public WeatherDetailFragment setCityId(long cityId) {
        this.cityId = cityId;
        return this;
    }
}
