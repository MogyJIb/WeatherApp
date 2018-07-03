package by.intervale.wetherapp.views.search;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import by.intervale.wetherapp.Application;
import by.intervale.wetherapp.R;
import by.intervale.wetherapp.data.models.City;
import by.intervale.wetherapp.views.base.BaseDialogFragment;
import by.intervale.wetherapp.views.cities.CityRecyclerViewAdapter;

public class SearchCityDialogFragment
        extends BaseDialogFragment
        implements ISearchCityView {

    private static String LISTENER_TAG = OnSearchResultListener.class.getSimpleName();

    @BindView(R.id.fr_search_city__cities)
    RecyclerView mRecyclerView;

    @BindView(R.id.fr_search_city__search_view)
    SearchView mSearchView;

    @Inject
    ISearchCityPresenter mPresenter;

    private OnSearchResultListener mOnSearchResultListener;

    private CityRecyclerViewAdapter mRecyclerViewAdapter;

    public static SearchCityDialogFragment newInstance(OnSearchResultListener searchResultListener){
        SearchCityDialogFragment fragment = new SearchCityDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable(LISTENER_TAG, searchResultListener);
        fragment.setArguments(args);
        return fragment;
    }

    public SearchCityDialogFragment() {
        super();
        mRecyclerViewAdapter = new CityRecyclerViewAdapter();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE,R.style.search_dialog);
        if(getArguments()!=null) {
            mOnSearchResultListener =
                    (OnSearchResultListener) getArguments().getSerializable(LISTENER_TAG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout to use as dialog or embedded fragment
        return inflater.inflate(R.layout.dialog_fragment_search_city, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Application.applicationComponent().inject(this);

        mRecyclerViewAdapter.setOnItemClickListener(city -> {
            mOnSearchResultListener.onSearchResult(city);
            dismiss();
        });

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.cities_list_divider));
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mPresenter.searchCities(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                mPresenter.searchCities(newText);
                return false;
            }
        });
        mSearchView.setOnCloseListener(() -> {
            mRecyclerViewAdapter.clear();
            return false;
        });

        mPresenter.bindView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.unbindView();
        mOnSearchResultListener = null;
    }

    @Override
    public void updateData(List<City> cities) {
        mRecyclerViewAdapter.updateData(cities);
    }

    @Override
    public void clearData() {
        mRecyclerViewAdapter.clear();
    }

    public interface OnSearchResultListener extends Serializable{
        public void onSearchResult(City city);
    }
}
