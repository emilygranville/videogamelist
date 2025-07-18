package com.emilygranville.videogamelist.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emilygranville.videogamelist.databinding.FragmentAboutVgViewBinding;

public class AboutVGView extends Fragment {

    public static final String FRAG_NAME = "about";

    private FragmentAboutVgViewBinding binding;
    private final IAboutVGView.Listener listener;

    /**
     * Constructors for AboutVGView
     */
    public AboutVGView(IAboutVGView.Listener listener) {
        this.listener = listener;
    }

    /**
     *
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
     * @return
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentAboutVgViewBinding.inflate(inflater);
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
        displayFragment();
    }

    /**
     * Saves information about the fragment before the
     * fragment is deleted
     * @param outState Bundle in which to place your saved state.
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
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
            this.listener.restoreAboutFragment(this);
        }
        displayFragment();
    }

    /**
     * Rest of the code necessary for displaying the fragment
     */
    private void displayFragment() {
        this.binding.aboutReturnBtn.setOnClickListener(view -> AboutVGView.this.listener.onReturnToDisplay());
    }
}