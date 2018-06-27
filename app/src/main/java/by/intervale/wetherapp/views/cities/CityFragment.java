package by.intervale.wetherapp.views.cities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import by.intervale.wetherapp.Application;
import by.intervale.wetherapp.R;
import by.intervale.wetherapp.models.City;
import by.intervale.wetherapp.views.base.BaseFragment;


public class CityFragment
        extends BaseFragment
        implements ICityView{

    @OnClick(R.id.fr_city__fab)
    public void onFabClick(){
    }


    @BindView(R.id.fr_city_list__cities)
    RecyclerView mRecyclerView;

    @Inject
    ICityPresenter mPresenter;

    private CityRecyclerViewAdapter mRecyclerViewAdapter;

    public CityFragment() {
        super();
        mRecyclerViewAdapter = new CityRecyclerViewAdapter();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_city_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Application.applicationComponent().inject(this);

        initRecyclerView();

        mPresenter.bindView(this);
        if(savedInstanceState==null) mPresenter.loadCities();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.unbindView();
    }

    @Override
    public void updateData(List<City> cities) {
        mRecyclerViewAdapter.updateData(cities);
    }

    private void initRecyclerView(){
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.cities_list_divider));
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }
}
