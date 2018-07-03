package by.intervale.wetherapp.views;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import by.intervale.wetherapp.Application;
import by.intervale.wetherapp.R;
import by.intervale.wetherapp.views.base.BaseActivity;
import by.intervale.wetherapp.views.cities.CityFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Application.applicationComponent().inject(this);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        openFragment(new CityFragment());
    }

    @Override
    protected @IdRes int getContainerId() {
        return R.id.act_main__container;
    }
}
