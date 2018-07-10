package by.intervale.wetherapp.views.weather.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.BindView;
import by.intervale.wetherapp.Application;
import by.intervale.wetherapp.R;
import by.intervale.wetherapp.views.base.BaseFragment;
import by.intervale.wetherapp.views.weather.fiveday.Weather5DayFragment;
import by.intervale.wetherapp.views.weather.intime.WeatherInTimeFragment;

public class WeatherDetailFragment
        extends BaseFragment
        implements IWeatherDetailView {

    @BindView(R.id.fr_weather_detail__tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.fr_weather_detail__viewpager)
    ViewPager mViewPager;

    @Inject
    IWeatherDetailPresenter mPresenter;

    private long cityId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Application.applicationComponent().inject(this);

        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                    switch (position) {
                        case 0: return new WeatherInTimeFragment().setCityId(cityId);
                        case 1: return new Weather5DayFragment().setCityId(cityId);
                        default: return null;
                    }
                }

                @Override
                public int getCount() {
                    return 2;
                }

                @Override
                public CharSequence getPageTitle(int position) {
                    switch (position) {
                        case 0: return "Now";
                        case 1: return "5day";
                        default: return null;
                    }
                }
            }
        );

        mTabLayout.setupWithViewPager(mViewPager);
    }

    public WeatherDetailFragment setCityId(long cityId) {
        this.cityId = cityId;
        return this;
    }
}
