package com.emilygranville.videogamelist.View;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.emilygranville.videogamelist.databinding.ActivityMainBinding;

public class MainView implements IMainView {

    FragmentManager fragmentManager;
    private ActivityMainBinding binding;
    private Listener listener;

    /**
     * MainView constructor
     * @param activity android activity
     */
    public MainView(Listener listener, FragmentActivity activity) {
        this.fragmentManager = activity.getSupportFragmentManager();
        this.binding = ActivityMainBinding.inflate(activity.getLayoutInflater());
        this.listener = listener;
    }

    /**
     * Returns highest root view of the screen
     */
    @Override
    public View getRootView() {
        return this.binding.getRoot();
    }

    /**
     * Displays the fragment
     * @param fragment
     * @param allowBack whether back button can return to fragment
     * @param name
     */
    @Override
    public void displayFragment(Fragment fragment, boolean allowBack, String name) {
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        ft.replace(this.binding.fragmentContainerView.getId(), fragment);
        if (allowBack) {
            ft.addToBackStack(name);
        }
        ft.commit();
    }
}
