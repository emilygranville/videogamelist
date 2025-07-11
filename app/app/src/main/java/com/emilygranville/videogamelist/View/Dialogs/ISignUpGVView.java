package com.emilygranville.videogamelist.View.Dialogs;

public interface ISignUpGVView {
    interface Listener {

        /**
         * Alerts listener to create account button
         *
         * @param email email to save
         * @param password password to save
         */
        public void onCreateAccount(String email, String password);
    }
}
