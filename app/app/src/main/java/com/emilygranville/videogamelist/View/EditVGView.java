package com.emilygranville.videogamelist.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emilygranville.videogamelist.databinding.FragmentEditVgViewBinding;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

public class EditVGView extends Fragment implements IEditVVGView {

    private FragmentEditVgViewBinding binding;
    private IEditVVGView.Listener listener;
    private List<String> consoleOptions;

    public EditVGView() {
        // Required empty public constructor
    }

    public EditVGView(Listener listener, List<String> consoleOptions) {
        this.listener = listener;
        this.consoleOptions = consoleOptions;
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
        this.binding = FragmentEditVgViewBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void
    onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ChipGroup consoleChipGroup = this.binding.consoleChipGroup;
        for (String console : this.consoleOptions) {
            Log.i("vgl", console);
            Chip consoleChip = new Chip(this.getContext());
            consoleChip.setText(console);
            consoleChipGroup.addView(consoleChip);
        }

        this.binding.submitVideoGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditVGView.this.listener.submitGame();
            }
        });
    }
}