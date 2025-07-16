package com.emilygranville.videogamelist.View.Dialogs;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emilygranville.videogamelist.databinding.FragmentConfirmVgDialogBinding;

public class ConfirmVGDialog extends DialogFragment implements IConfirmVGDialog {

    public static final String FRAG_NAME = "confirm";

    private FragmentConfirmVgDialogBinding binding;
    private final IConfirmVGDialog.Listener listener;
    private final String purpose;


    public ConfirmVGDialog(IConfirmVGDialog.Listener listener, String purpose) {
        this.listener = listener;
        this.purpose = purpose;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentConfirmVgDialogBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.binding.confirmFalse.setOnClickListener(view1 -> ConfirmVGDialog.this.dismiss());
        this.binding.confirmTrue.setOnClickListener(view2 -> {
            ConfirmVGDialog.this.dismiss();
            ConfirmVGDialog.this.listener.onConfirm(ConfirmVGDialog.this.purpose);
        });
    }
}