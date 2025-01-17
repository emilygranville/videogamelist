package com.emilygranville.videogamelist.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emilygranville.videogamelist.R;
import com.emilygranville.videogamelist.databinding.FragmentDisplayVgViewBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DisplayVGView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DisplayVGView extends Fragment implements IDisplayVGView {

    FragmentDisplayVgViewBinding binding;
    Listener listener;


    /**
     * Required empty constructor
     */
    public DisplayVGView() {
        // Required empty public constructor
    }

    /**
     * Constructor for display view
     * @param listener listens for edit and delete buttons
     */
    public DisplayVGView(Listener listener) {
        this.listener = listener;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DisplayVGView.
     */
    // TODO: Rename and change types and number of parameters
    public static DisplayVGView newInstance(String param1, String param2) {
        DisplayVGView fragment = new DisplayVGView();
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
        return inflater.inflate(R.layout.fragment_display_vg_view, container, false);
    }
}