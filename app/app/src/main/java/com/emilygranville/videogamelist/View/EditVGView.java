package com.emilygranville.videogamelist.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emilygranville.videogamelist.Model.VideoGame;
import com.emilygranville.videogamelist.databinding.FragmentEditVgViewBinding;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

public class EditVGView extends Fragment implements IEditVVGView {

    private FragmentEditVgViewBinding binding;
    private IEditVVGView.Listener listener;
    private List<String> consoleOptions;
    private boolean isEdited;
    private VideoGame videoGame;

    /**
     * Constructors for EditVGView
     */
    public EditVGView() {
        // Required empty public constructor
    }

    public EditVGView(Listener listener, List<String> consoleOptions) {
        this.listener = listener;
        this.consoleOptions = consoleOptions;
        this.isEdited = false;
    }

    public EditVGView(Listener listener, List<String> consoleOptions, VideoGame videoGame) {
        this.listener = listener;
        this.consoleOptions = consoleOptions;
        this.videoGame = videoGame;
        this.isEdited = true;
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
        for (int i = 0; i < this.consoleOptions.size(); i++) {
            String console = this.consoleOptions.get(i);
            Chip consoleChip = new Chip(this.getContext());
            //consoleChip.setCloseIconVisible(true);
            consoleChip.setCheckable(true);
            consoleChip.setText(console);
            consoleChip.setId(i);
            consoleChipGroup.addView(consoleChip);
        }

        if(isEdited) {
            this.binding.editNameInput.setText(videoGame.getGameName());
            double price = videoGame.getPrice();
            if (price != 0) {
                this.binding.editPriceInput.setText(String.valueOf(price));
            }
            int numChips = this.binding.consoleChipGroup.getChildCount();
            for (int i = 0; i < numChips; i++) {
                Chip chip = (Chip) this.binding.consoleChipGroup.getChildAt(i);
                if (videoGame.getConsoles().contains(chip.getText())) {
                    chip.setChecked(true);
                }
            }
        }

        this.binding.addConsoleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentAddConsoleDialog dialogFragment = new FragmentAddConsoleDialog();
                dialogFragment.show(getParentFragmentManager(),"My  Fragment");
            }
        });

        this.binding.submitVideoGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gameName = EditVGView.this.binding.editNameInput.getText().toString();
                double price;
                try {
                    price = Double.parseDouble(EditVGView.this.binding.editPriceInput.getText().toString());
                } catch (NumberFormatException e) {
                    Log.e("vgl", e.toString());
                    price = 0;
                }
                List<Integer> selectedIndices = EditVGView.this.binding.consoleChipGroup.getCheckedChipIds();
                List<String> selectedConsoles = new ArrayList<>();
                for (int index : selectedIndices) {
                    selectedConsoles.add(EditVGView.this.consoleOptions.get(index));
                }
                VideoGame newGame;
                if (isEdited) {
                    newGame = new VideoGame(gameName, price, selectedConsoles, EditVGView.this.videoGame.getGameId());
                } else {
                    newGame = new VideoGame(gameName, price, selectedConsoles);
                }
                EditVGView.this.listener.submitGame(newGame);
            }
        });
    }
}