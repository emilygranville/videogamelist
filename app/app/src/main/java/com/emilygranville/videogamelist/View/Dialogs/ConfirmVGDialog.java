package com.emilygranville.videogamelist.View.Dialogs;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emilygranville.videogamelist.R;
import com.emilygranville.videogamelist.databinding.FragmentAddConsoleDialogBinding;
import com.emilygranville.videogamelist.databinding.FragmentConfirmVgDialogBinding;

public class ConfirmVGDialog extends DialogFragment {

    public static final String FRAG_NAME = "confirm";

    private FragmentConfirmVgDialogBinding binding;


    public ConfirmVGDialog() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentConfirmVgDialogBinding.inflate(inflater);
        return this.binding.getRoot();    }
}