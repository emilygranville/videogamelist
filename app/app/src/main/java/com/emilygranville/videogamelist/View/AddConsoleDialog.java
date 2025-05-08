package com.emilygranville.videogamelist.View;

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

public class AddConsoleDialog extends DialogFragment implements IAddConsoleDialog {

    public static final String FRAG_NAME = "dialog";

    private FragmentAddConsoleDialogBinding binding;
    private IAddConsoleDialog.Listener listener;

    public AddConsoleDialog() {
        // Required empty public constructor
    }

    public AddConsoleDialog(Listener listener) {
        this.listener = listener;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentAddConsoleDialog.
     */
    // TODO: Rename and change types and number of parameters
//    public static AddConsoleDialog newInstance(String param1, String param2) {
//        AddConsoleDialog fragment = new AddConsoleDialog();
//        Bundle args = new Bundle();
//
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.binding = FragmentAddConsoleDialogBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // from https://stackoverflow.com/a/45264822 on StackOverflow
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        Dialog dialog = this.getDialog();
        dialog.getWindow().setLayout((6 * width)/7, (int) (height/2.65));

        this.binding.addConsoleDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddConsoleDialog.this.dismiss();
            }
        });

        this.binding.addConsoleSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String consoleName = AddConsoleDialog.this.binding.editNewConsoleInput.getText().toString();
                if(!consoleName.equals("")) {
                    AddConsoleDialog.this.listener.submitNewConsole(consoleName);
                    AddConsoleDialog.this.dismiss();
                } else {
                    Snackbar.make(AddConsoleDialog.this.binding.getRoot(),
                            getString(R.string.not_valid_new_console), Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
}