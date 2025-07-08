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
    private IAboutVGView.Listener listener;
    private boolean hasInitInfo;

    /**
     * Constructors for AboutVGView
     */
    public AboutVGView(IAboutVGView.Listener listener) {
        this.listener = listener;
        this.hasInitInfo = false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentAboutVgViewBinding.inflate(inflater);
        return this.binding.getRoot();
    }

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
    }

    private void displayFragment() {
        this.binding.aboutReturnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AboutVGView.this.listener.returnToDisplayFromAbout();
            }
        });
    }
}