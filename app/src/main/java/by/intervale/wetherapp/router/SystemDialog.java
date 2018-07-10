package by.intervale.wetherapp.router;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;

import java.util.UUID;

import ru.terrakok.cicerone.commands.Command;

public class SystemDialog implements Command {
    private UUID tag;
    private DialogFragment dialog;

    public SystemDialog(DialogFragment dialog) {
        this.dialog = dialog;
        tag = UUID.randomUUID();
    }

    public DialogFragment getDialog() {
        return dialog;
    }

    public String getTag() {
        return DialogFragment.class.getSimpleName() + tag.toString();
    }
}