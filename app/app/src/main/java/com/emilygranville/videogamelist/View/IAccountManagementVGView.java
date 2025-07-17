package com.emilygranville.videogamelist.View;

import androidx.fragment.app.Fragment;

public interface IAccountManagementVGView {
    /**
     * Add a listener for potential future use
     */
    interface Listener {
        void restoreAccountManagementFrag(Fragment curFragment);
//        boolean onSignUp();
//        boolean onSignIn();
        boolean onSignOut();
//        boolean onDeleteAccount();
        void onAMReturn();
    }

    void signUpPopUp();
    void signInPopUp();
    void newPWPopUp();
    void signOutPopUp();
    void deleteAccountPopUp();
}