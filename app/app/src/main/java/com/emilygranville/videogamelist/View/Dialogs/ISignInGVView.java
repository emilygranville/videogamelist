package com.emilygranville.videogamelist.View.Dialogs;

public interface ISignInGVView {
    interface Listener {

        /**
         * Alerts listener to sign into account button
         *
         * @param email email to save
         * @param password password to save
         * @param purpose the purpose for the sign in (sign in, sign up, etc)
         */
        public void onSignIn(String email, String password, String purpose);
    }
}
