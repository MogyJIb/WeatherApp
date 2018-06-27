package by.intervale.wetherapp.views;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import by.intervale.wetherapp.Application;
import by.intervale.wetherapp.R;
import by.intervale.wetherapp.models.City;
import by.intervale.wetherapp.models.Geometry;
import by.intervale.wetherapp.views.base.BaseActivity;
import by.intervale.wetherapp.views.cities.CityFragment;
import by.intervale.wetherapp.views.dialogs.SearchCityDialogFragment;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

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
