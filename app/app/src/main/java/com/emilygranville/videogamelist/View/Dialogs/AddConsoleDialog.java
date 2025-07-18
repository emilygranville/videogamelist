package com.emilygranville.videogamelist.View.Dialogs;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emilygranville.videogamelist.R;
import com.emilygranville.videogamelist.databinding.FragmentAddConsoleDialogBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class AddConsoleDialog extends DialogFragment implements IAddConsoleDialog {

    public static final String FRAG_NAME = "dialog";

    private FragmentAddConsoleDialogBinding binding;
    private final IAddConsoleDialog.Listener listener;

    public AddConsoleDialog(Listener listener) {
        this.listener = listener;
    }

    /**
     * Creates the dialog
     * @param savedInstanceState If the fragment is being re-created from
     * a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return the root of the binding
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentAddConsoleDialogBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    /**
     *
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // from https://stackoverflow.com/a/45264822 on StackOverflow
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        Dialog dialog = this.getDialog();
        assert dialog != null;
        Objects.requireNonNull(dialog.getWindow())
                .setLayout((6 * width)/7, (int) (height/2.65));

        this.binding.addConsoleDismiss.setOnClickListener(view1 -> AddConsoleDialog.this.dismiss());

        this.binding.addConsoleSubmit.setOnClickListener(view2 -> {
            String consoleName = Objects.requireNonNull(
                    AddConsoleDialog.this.binding.editNewConsoleInput.getText()).toString();
            if(!consoleName.isEmpty()) {
                AddConsoleDialog.this.listener.onSubmitNewConsole(consoleName);
                AddConsoleDialog.this.dismiss();
            } else {
                Snackbar.make(AddConsoleDialog.this.binding.getRoot(),
                        getString(R.string.not_valid_new_console), Snackbar.LENGTH_LONG).show();
            }
        });
    }
}