package com.emilygranville.videogamelist.View.Dialogs;

public interface IConfirmVGDialog {
    interface Listener {

        /**
         * Alerts listener to confirmation
         */
        void onConfirm(String purpose);
    }
}
