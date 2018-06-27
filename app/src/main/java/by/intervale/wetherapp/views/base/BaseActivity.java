package by.intervale.wetherapp.views.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Map;

import by.intervale.wetherapp.Application;
import by.intervale.wetherapp.R;

public abstract class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Application.applicationComponent().inject(this);
    }

    public void openFragment(Fragment fragment) {
        openFragment(fragment,true);
    }

    public void openFragment(Fragment fragment, boolean addInBackStack) {
        if (addInBackStack)
            getSupportFragmentManager().beginTransaction()
                    .replace(getContainerId(), fragment)
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commit();
        else
            getSupportFragmentManager().beginTransaction()
                    .replace(getContainerId(), fragment)
                    .commit();
    }

    public void openFragmentFromStack(Fragment fragment){
        String backStateName = fragment.getClass().getName();

        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);

        if (!fragmentPopped) //fragment not in back stack, create it.
            openFragment(fragment,true);

    }


    public void clearFragmentStack() {
        FragmentManager fm = getSupportFragmentManager();
        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }


    protected abstract @IdRes int getContainerId();
}
