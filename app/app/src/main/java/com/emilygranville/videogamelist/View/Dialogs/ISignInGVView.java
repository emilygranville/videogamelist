package com.emilygranville.videogamelist.View.Dialogs;

public interface ISignInGVView {
    interface Listener {

        /**
         * Alerts listener to sign into account button
         *
         * @param email email to save
         * @param password password to save
         */
        public void onSignIntoAccount(String email, String password);
    }
}
