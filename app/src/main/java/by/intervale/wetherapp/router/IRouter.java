package by.intervale.wetherapp.router;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;

import ru.terrakok.cicerone.result.ResultListener;

public interface IRouter {
    void setResultListener(Integer resultCode, ResultListener listener);
    void removeResultListener(Integer resultCode);

    void navigateTo(String screenKey);
    void navigateTo(String screenKey, Object data);

    void newScreenChain(String screenKey);
    void newScreenChain(String screenKey, Object data);

    void newRootScreen(String screenKey);
    void newRootScreen(String screenKey, Object data);

    void replaceScreen(String screenKey);
    void replaceScreen(String screenKey, Object data);

    void finishChain();

    void exit();
    void exitWithResult(Integer resultCode, Object result);
    void exitWithMessage(String message);

    void showSystemMessage(String message);

    void showSystemDialog(DialogFragment dialog);

    void back();
}
