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

/**
 * A simple {@link DialogFragment} subclass.
 * Use the {@link FragmentAddConsoleDialog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAddConsoleDialog extends DialogFragment {

    public FragmentAddConsoleDialog() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentAddConsoleDialog.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentAddConsoleDialog newInstance(String param1, String param2) {
        FragmentAddConsoleDialog fragment = new FragmentAddConsoleDialog();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

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
        return inflater.inflate(R.layout.fragment_add_console_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // from https://stackoverflow.com/a/45264822 on StackOverflow
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        Dialog yourDialog = this.getDialog();
        yourDialog.getWindow().setLayout((6 * width)/7, (int) (height/2.65));
    }
}