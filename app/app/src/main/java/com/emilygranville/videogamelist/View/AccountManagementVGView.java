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
import com.emilygranville.videogamelist.R;
import com.emilygranville.videogamelist.View.Dialogs.ISignInGVView;
import com.emilygranville.videogamelist.View.Dialogs.ISignUpGVView;
import com.emilygranville.videogamelist.View.Dialogs.SignInVGDialog;
import com.emilygranville.videogamelist.View.Dialogs.SignUpVGDialog;
import com.emilygranville.videogamelist.databinding.FragmentAccountManagementVgViewBinding;

public class AccountManagementVGView extends Fragment implements IAccountManagementVGView {

    public static final String FRAG_NAME = "account management";

    private FragmentAccountManagementVgViewBinding binding;
    private final IAccountManagementVGView.Listener listener;

    /**
     * Constructors for AboutVGView
     */
    public AccountManagementVGView(IAccountManagementVGView.Listener listener) {
        this.listener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentAccountManagementVgViewBinding.inflate(inflater);
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
            this.listener.restoreAccountManagementFrag(this);
        }
        displayFragment();
    }


    @Override
    public void onUserSignUp() {
        SignUpVGDialog dialogFragment = new SignUpVGDialog((ISignUpGVView.Listener)
                AccountManagementVGView.this.listener);
        dialogFragment.show(getParentFragmentManager(), SignUpVGDialog.FRAG_NAME);
    }

    @Override
    public void onUserSignIn() {
        SignInVGDialog dialogFragment = new SignInVGDialog((ISignInGVView.Listener)
                AccountManagementVGView.this.listener);
        dialogFragment.show(getParentFragmentManager(), SignInVGDialog.FRAG_NAME);
    }

    public void onUserSignOut() {
        Log.i(MainActivity.VGL, "sign out");
    }
    public void onUserDeleteAccount() {
        Log.i(MainActivity.VGL, "delete account");
    }

    private void displayFragment() {
        this.binding.amSigninBtn.setOnClickListener(view -> AccountManagementVGView.this.onUserSignIn());
        this.binding.amSignupBtn.setOnClickListener(view -> AccountManagementVGView.this.onUserSignUp());
    }
}