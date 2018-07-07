package by.intervale.wetherapp.views.weather.fiveday;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import by.intervale.wetherapp.Application;
import by.intervale.wetherapp.R;
import by.intervale.wetherapp.data.models.City;
import by.intervale.wetherapp.data.models.WeatherInTime;
import by.intervale.wetherapp.views.base.BaseFragment;
import by.intervale.wetherapp.views.weather.intime.WeatherInTimeFragment;

public class Weather5DayFragment extends BaseFragment
        implements IWeather5DayView {

    @BindView(R.id.fr_weather_5day__title)
    TextView mTitle;
    @BindView(R.id.fr_weather_5day__weather_list)
    RecyclerView mRecyclerView;

    @Inject
    IWeather5DayPresenter mPresenter;

    private long cityId;
    private Weather5DayRecyclerViewAdapter mRecyclerViewAdapter;

    public Weather5DayFragment() {
        super();
        mRecyclerViewAdapter = new Weather5DayRecyclerViewAdapter();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_5day_weather, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Application.applicationComponent().inject(this);

        mRecyclerViewAdapter.setOnItemClickListener(weatherInTime -> {
//            WeatherInTimeFragment fragment = new WeatherInTimeFragment()
//                    .setCityId(weatherInTime.cityId);
//            getFragmentManager().beginTransaction()
//                    .replace(R.id.act_main__container, fragment, WeatherInTimeFragment.class.getSimpleName())
//                    .addToBackStack(WeatherInTimeFragment.class.getSimpleName())
//                    .commit();
        });

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.list_divider));
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        mPresenter.bindView(this);
        if (savedInstanceState == null) mPresenter.loadWeatherData(cityId);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.unbindView();
    }

    public Weather5DayFragment setCityId(long cityId) {
        this.cityId = cityId;
        return this;
    }

    @Override
    public void setTitle(String title) {
        mTitle.setText(title);
    }

    @Override
    public void updateListData(List<WeatherInTime> weathers) {
        mRecyclerViewAdapter.updateData(weathers);
    }
}