package by.intervale.wetherapp.router;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.SupportAppNavigator;
import ru.terrakok.cicerone.commands.Command;

public abstract class AppNavigator extends SupportAppNavigator {
    private FragmentActivity activity;

    public AppNavigator(FragmentActivity activity, int containerId) {
        super(activity, containerId);
        this.activity = activity;
    }

    @Override
    protected void applyCommand(Command command) {
        if (command instanceof SystemDialog)
            showSystemDialog((SystemDialog) command);
        else
            super.applyCommand(command);
    }

    protected void showSystemDialog(SystemDialog systemDialog) {
        systemDialog.getDialog().show(
                activity.getSupportFragmentManager(), systemDialog.getTag());
    }
}
