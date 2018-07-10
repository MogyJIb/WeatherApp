package by.intervale.wetherapp.router;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.commands.Back;

public class AppRouter extends Router implements IRouter{
    /**
     * Show system dialog.
     *
     * @param dialog dialog to show
     */
    public void showSystemDialog(DialogFragment dialog) {
        executeCommands(new SystemDialog(dialog));
    }

    public void back(){
        executeCommands(new Back());
    }
}
