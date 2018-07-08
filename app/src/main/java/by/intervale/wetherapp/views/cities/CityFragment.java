package by.intervale.wetherapp.views.cities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import by.intervale.wetherapp.Application;
import by.intervale.wetherapp.R;
import by.intervale.wetherapp.data.models.City;
import by.intervale.wetherapp.views.base.BaseFragment;
import by.intervale.wetherapp.views.base.BaseRecyclerViewAdapter;


public class CityFragment
        extends BaseFragment
        implements ICityView {

    @BindView(R.id.fr_city_list__cities)
    RecyclerView mRecyclerView;

    @Inject
    ICityPresenter mPresenter;
    private ActionMode mActionMode;

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

        mRecyclerViewAdapter.setOnItemClickListener(mPresenter::onItemClick);
        mRecyclerViewAdapter.setOnItemLongClickListener(this::onLongClick);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                mRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.list_divider));
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        mPresenter.bindView(this);
        if (savedInstanceState == null) mPresenter.loadCities();
    }

    private boolean onLongClick(City city, View view){
        mActionMode = getActivity().startActionMode(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                actionMode.getMenuInflater()
                        .inflate(R.menu.context_menu_favourite_city, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.cm__delete:
                        mPresenter.deleteFromFavourite(city);
                        mActionMode.finish(); // Action picked, so close the CAB
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {
                mActionMode = null;
            }
        });
        view.setSelected(true);
        return true;
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

    @OnClick(R.id.fr_city__fab_add)
    public void onAddButtonClick() {
        mPresenter.onAddButtonClick();
    }
}
