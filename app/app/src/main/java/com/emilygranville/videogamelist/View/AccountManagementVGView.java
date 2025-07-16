package com.emilygranville.videogamelist.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emilygranville.videogamelist.R;
import com.emilygranville.videogamelist.View.Dialogs.ConfirmVGDialog;
import com.emilygranville.videogamelist.View.Dialogs.IConfirmVGDialog;
import com.emilygranville.videogamelist.View.Dialogs.ISignInGVView;
import com.emilygranville.videogamelist.View.Dialogs.SignInVGDialog;
import com.emilygranville.videogamelist.databinding.FragmentAccountManagementVgViewBinding;

public class AccountManagementVGView extends Fragment implements IAccountManagementVGView, IConfirmVGDialog.Listener {

    public static final String FRAG_NAME = "account management";

    private static final String SIGN_OUT_PURPOSE_KEY = "sign out";
    private static final String DELETE_ACC_PURPOSE_KEY = "delete account";

    public static final String SIGN_IN_PURPOSE_KEY = "sign in";
    public static final String SIGN_UP_PURPOSE_KEY = "sign up";
    public static final String CONFIRM_ACCOUNT_PURPOSE_KEY = "confirm account";

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

    /**
     * Internally handles sign up button
     */
    @Override
    public void signUpPopUp() {
        String msg = getResources().getString(R.string.signup_txt);
        SignInVGDialog dialogFragment = new SignInVGDialog((ISignInGVView.Listener)
                AccountManagementVGView.this.listener, SIGN_UP_PURPOSE_KEY, msg);
        dialogFragment.show(getParentFragmentManager(), SignInVGDialog.FRAG_NAME);
    }

    /**
     * Internally handles sign in button
     */
    @Override
    public void signInPopUp() {
        String msg = getResources().getString(R.string.signin_txt);
        SignInVGDialog dialogFragment = new SignInVGDialog((ISignInGVView.Listener)
                AccountManagementVGView.this.listener, SIGN_IN_PURPOSE_KEY, msg);
        dialogFragment.show(getParentFragmentManager(), SignInVGDialog.FRAG_NAME);
    }

    /**
     * Internally handles sign out button
     */
    public void signOutPopUp() {
        ConfirmVGDialog confirmVGDialog = new ConfirmVGDialog(this, SIGN_OUT_PURPOSE_KEY);
        confirmVGDialog.show(getParentFragmentManager(), ConfirmVGDialog.FRAG_NAME);
    }

    /**
     * Internally handles delete account button
     */
    public void deleteAccountPopUp() {
        ConfirmVGDialog confirmVGDialog = new ConfirmVGDialog(this, DELETE_ACC_PURPOSE_KEY);
        confirmVGDialog.show(getParentFragmentManager(), ConfirmVGDialog.FRAG_NAME);
    }

    /**
     * Sets up the display for the fragment
     */
    private void displayFragment() {
        this.binding.amSigninBtn.setOnClickListener(view -> AccountManagementVGView.this.signInPopUp());
        this.binding.amSignupBtn.setOnClickListener(view -> AccountManagementVGView.this.signUpPopUp());
        this.binding.amSignoutBtn.setOnClickListener(view -> AccountManagementVGView.this.signOutPopUp());
        this.binding.amDeleteAccountBtn.setOnClickListener(view -> AccountManagementVGView.this.deleteAccountPopUp());
        this.binding.amReturnBtn.setOnClickListener(view -> this.listener.onAMReturn());
    }

    /**
     * Alerts listener to confirmation
     */
    @Override
    public void onConfirm(String purpose) {
        if (purpose.equals(SIGN_OUT_PURPOSE_KEY)) {
            this.listener.onSignOut();
        } else if (purpose.equals(DELETE_ACC_PURPOSE_KEY)) {
            this.listener.onDeleteAccount();
        }
    }
}