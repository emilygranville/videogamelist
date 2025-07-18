package com.emilygranville.videogamelist.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emilygranville.videogamelist.Controller.MainActivity;
import com.emilygranville.videogamelist.Model.VideoGame;
import com.emilygranville.videogamelist.R;
import com.emilygranville.videogamelist.View.Dialogs.AddConsoleDialog;
import com.emilygranville.videogamelist.View.Dialogs.IAddConsoleDialog;
import com.emilygranville.videogamelist.databinding.FragmentEditVgViewBinding;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EditVGView extends Fragment implements IEditVVGView {

    public static final String FRAG_NAME = "edit";
    public static final String IS_EDITED_KEY = "is edited";

    private FragmentEditVgViewBinding binding;
    private final IEditVVGView.Listener listener;
    private List<String> consoleOptions;
    private boolean isEdited;
    private VideoGame videoGame;
    private final boolean hasInitInfo;

    /**
     * Constructors for EditVGView
     */
    public EditVGView(Listener listener) {
        this.listener = listener;
        this.hasInitInfo = false;
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
        this.binding = FragmentEditVgViewBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    /**
     *
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    @Override
    public void
    onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null && !hasInitInfo) {
            this.consoleOptions = (List<String>) getArguments().getSerializable(MainActivity
                    .CONSOLE_LIST_KEY);
            this.isEdited = getArguments().getBoolean(MainActivity.IS_EDITED_KEY);
            try {
                this.videoGame = (VideoGame) getArguments().getSerializable(MainActivity
                        .VIDEO_GAME_KEY);
            } catch (NullPointerException e) {
                Log.e("vgl", e.toString());
            }
            this.isEdited = getArguments().getBoolean(IS_EDITED_KEY);
        }

        ChipGroup consoleChipGroup = this.binding.consoleChipGroup;
        for (int i = 0; i < this.consoleOptions.size(); i++) {
            String console = this.consoleOptions.get(i);
            Chip consoleChip = new Chip(this.getContext());
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
            this.binding.editFavoriteBtn.setChecked(videoGame.getIsFavorite());
            int numChips = this.binding.consoleChipGroup.getChildCount();
            for (int i = 0; i < numChips; i++) {
                Chip chip = (Chip) this.binding.consoleChipGroup.getChildAt(i);
                if (videoGame.getConsoles().contains(chip.getText())) {
                    chip.setChecked(true);
                }
            }
        }

        this.binding.addConsoleButton.setOnClickListener(view1 -> {
            AddConsoleDialog dialogFragment = new AddConsoleDialog((IAddConsoleDialog.Listener)
                    EditVGView.this.listener);
            dialogFragment.show(getParentFragmentManager(), AddConsoleDialog.FRAG_NAME);
        });

        this.binding.submitVideoGame.setOnClickListener(view2 -> {
            String gameName = Objects.requireNonNull(
                    EditVGView.this.binding.editNameInput.getText()).toString();
            double price;
            try {
                price = Double.parseDouble(Objects.requireNonNull(
                        EditVGView.this.binding.editPriceInput.getText()).toString());
            } catch (NumberFormatException e) {
                Log.e("vgl", e.toString());
                price = 0;
            }
            boolean isFav = EditVGView.this.binding.editFavoriteBtn.isChecked();
            List<Integer> selectedIndices = EditVGView.this.binding.consoleChipGroup
                    .getCheckedChipIds();
            List<String> selectedConsoles = new ArrayList<>();
            for (int index : selectedIndices) {
                selectedConsoles.add(EditVGView.this.consoleOptions.get(index));
            }
            if(!gameName.isEmpty() && !selectedConsoles.isEmpty()) {
                VideoGame newGame;
                if (isEdited) {
                    newGame = new VideoGame(gameName, price, selectedConsoles, isFav,
                            EditVGView.this.videoGame.getGameId());
                } else {
                    newGame = new VideoGame(gameName, price, selectedConsoles, isFav);
                }
                EditVGView.this.listener.submitGame(newGame);
            } else {
                Snackbar.make(EditVGView.this.binding.getRoot(),
                        getString(R.string.not_valid_new_game),
                        Snackbar.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Saves information about the fragment before the
     * fragment is deleted
     * @param outState Bundle in which to place your saved state.
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(MainActivity.CONSOLE_LIST_KEY, (Serializable) this.consoleOptions);
        if (videoGame != null) {
            outState.putSerializable(MainActivity.VIDEO_GAME_KEY, this.videoGame);
        }
        outState.putBoolean(IS_EDITED_KEY, this.isEdited);
    }

    /**
     * Restores the View
     * @param savedInstanceState If the fragment is being re-created from
     * a previous saved state, this is the state.
     */
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            this.consoleOptions = (List<String>) savedInstanceState.getSerializable(MainActivity
                    .CONSOLE_LIST_KEY);
            try {
                this.videoGame = (VideoGame) savedInstanceState.getSerializable(MainActivity
                        .VIDEO_GAME_KEY);
            } catch (NullPointerException e) {
                Log.e("vgl", e.toString());
            }
            this.isEdited = savedInstanceState.getBoolean(IS_EDITED_KEY);
            this.listener.restoreEditFragment(this);
        }
    }

    /**
     * Shows the new console in the list of consoles
     * @param consoleName name of console to show
     */
    public void showNewConsole(String consoleName) {
        int newIndex = this.consoleOptions.size();
        this.consoleOptions.add(consoleName);

        ChipGroup consoleChipGroup = this.binding.consoleChipGroup;
        Chip consoleChip = new Chip(this.getContext());
        consoleChip.setCheckable(true);
        consoleChip.setText(consoleName);
        consoleChip.setId(newIndex);
        consoleChip.setChecked(true);
        consoleChipGroup.addView(consoleChip);
    }
}