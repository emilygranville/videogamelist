package com.emilygranville.videogamelist.Controller;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.emilygranville.videogamelist.R;
import com.emilygranville.videogamelist.View.AboutVGView;
import com.emilygranville.videogamelist.View.AccountManagementVGView;
import com.emilygranville.videogamelist.View.Dialogs.ISignInGVView;
import com.emilygranville.videogamelist.View.EditVGView;
import com.emilygranville.videogamelist.Model.ConsoleOrganizer;
import com.emilygranville.videogamelist.Model.VideoGame;
import com.emilygranville.videogamelist.View.DisplayVGView;
import com.emilygranville.videogamelist.View.IAboutVGView;
import com.emilygranville.videogamelist.View.Dialogs.IAddConsoleDialog;
import com.emilygranville.videogamelist.View.IAccountManagementVGView;
import com.emilygranville.videogamelist.View.ICloudDataPreservation;
import com.emilygranville.videogamelist.View.IDisplayVGView;
import com.emilygranville.videogamelist.View.IEditVVGView;
import com.emilygranville.videogamelist.View.IMainView;
import com.emilygranville.videogamelist.View.MainView;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainView.Listener,
        IDisplayVGView.Listener, IEditVVGView.Listener, IAddConsoleDialog.Listener,
        IAboutVGView.Listener, ISignInGVView.Listener,
        IAccountManagementVGView.Listener, ICloudDataPreservation.Listener {

    private static final String CONSOLE_ORGANIZER_KEY = "console organizer";
    private static final String IN_PROGRESS_KEY = "in progress";
    public static final String VIDEO_GAME_KEY = "video game";
    public static final String CONSOLE_LIST_KEY = "console list";
    public static final String GAMES_FOR_CONSOLE = "games for console";
    public static final String CONSOLE_NAME_KEY = "console name";
    public static final String SCROLL_LEFT_KEY = "scroll left";
    public static final String IS_EDITED_KEY = "is edited";

    public static final String VGL = "vgl";

    public static final String FAVORITES_KEY = "Favorites";

    private IMainView mainView;
    private ConsoleOrganizer consoleOrganizer;
    private Fragment currentFragment;

    private FirebaseAuth auth;

    /*
     * ANDROID METHODS
     */

    /**
     * Sets up the app
     * @param savedInstanceState saved information when app reloads
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportFragmentManager().
                setFragmentFactory(new VGLFragmentFactory(this));

        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();

        this.mainView = new MainView(this);
        setContentView(this.mainView.getRootView());

        if (savedInstanceState != null) {
            this.consoleOrganizer = (ConsoleOrganizer) savedInstanceState
                    .getSerializable(CONSOLE_ORGANIZER_KEY);
        } else {

            loadLocally();

            if(this.consoleOrganizer == null || !this.consoleOrganizer.getConsoleList().isEmpty()) {
                assert this.consoleOrganizer != null;
                showDisplayFrag(this.consoleOrganizer.getConsoleList().get(0));
            } else {
                showDisplayFrag(null);
            }
        }
    }

    /**
     *  Saves progress when resource constraints are destroyed
     * @param outState what saves the information
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(IN_PROGRESS_KEY, true);
        outState.putSerializable(CONSOLE_ORGANIZER_KEY, consoleOrganizer);
    }

    /**
     * Restores the MainActivity
     * @param savedInstanceState the saved info to restore
     */
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.consoleOrganizer = (ConsoleOrganizer) savedInstanceState
                .getSerializable(CONSOLE_ORGANIZER_KEY);
    }

    /*
     * NORMAL METHODS
     */

    /**
     * Loads the data from the device (locally)
     */
    private void loadLocally() {
        IDataPreservation loadData = new LocalDataPreservation();
        this.consoleOrganizer = loadData.loadConsoleOrganizer(this);
    }

    /**
     * Saves data locally to device
     */
    private boolean saveLocally() {
        IDataPreservation saveData = new LocalDataPreservation();
        return saveData.saveConsoleOrganizer(this, this.consoleOrganizer);
    }

    /**
     * Shows the Account Management page
     */
    private void showAccountManagementFrag() {
        this.currentFragment = new AccountManagementVGView(this);
        this.mainView.displayFragment(currentFragment, true,
                AccountManagementVGView.FRAG_NAME);
    }

    /**
     * Shows the About page
     */
    private void showAboutFrag() {
        this.currentFragment = new AboutVGView(this);
        this.mainView.displayFragment(currentFragment, true, AboutVGView.FRAG_NAME);
    }

    /**
     * Shows the Display page
     * @param console name of the console to display
     */
    private void showDisplayFrag(String console) {
        showDisplayFrag(console, 0);
    }

    /**
     * Shows the Display page
     * @param console name of the console to display
     */
    private void showDisplayFrag(String console, int scrollLeft) {
        List<VideoGame> gamesForConsole;

        if (console == null) {
            console = MainActivity.FAVORITES_KEY;
        }

        if (!console.equals(MainActivity.FAVORITES_KEY)) {
            gamesForConsole = this.consoleOrganizer.getGamesForConsole(console);
        } else {
            gamesForConsole = this.consoleOrganizer.getFavorites();
        }

        List<String> consoleList = this.consoleOrganizer.getConsoleList();
        Bundle fragArgs = new Bundle();
        fragArgs.putSerializable(GAMES_FOR_CONSOLE, (Serializable) gamesForConsole);
        fragArgs.putSerializable(CONSOLE_LIST_KEY, (Serializable) consoleList);
        fragArgs.putString(CONSOLE_NAME_KEY, console);
        fragArgs.putInt(SCROLL_LEFT_KEY, scrollLeft);
        this.currentFragment = new DisplayVGView(this);
        this.currentFragment.setArguments(fragArgs);
        this.mainView.displayFragment(currentFragment, false, DisplayVGView.FRAG_NAME);
    }

    /**
     * Shows the Edit page
     */
    private void showEditFrag(){
        Bundle fragArgs = new Bundle();
        fragArgs.putSerializable(CONSOLE_LIST_KEY, (Serializable) this.consoleOrganizer
                .getConsoleList());
        fragArgs.putBoolean(IS_EDITED_KEY, false);
        this.currentFragment = new EditVGView(this);
        currentFragment.setArguments(fragArgs);
        this.mainView.displayFragment(currentFragment, true, EditVGView.FRAG_NAME);
    }

    /**
     * Shows the Edit page
     * @param videoGame VideoGame to edit
     */
    private void showEditFrag(VideoGame videoGame){
        Bundle fragArgs = new Bundle();
        fragArgs.putSerializable(VIDEO_GAME_KEY, videoGame);
        fragArgs.putSerializable(CONSOLE_LIST_KEY, (Serializable) this.consoleOrganizer
                .getConsoleList());
        fragArgs.putBoolean(IS_EDITED_KEY, true);
        this.currentFragment = new EditVGView(this);
        currentFragment.setArguments(fragArgs);
        this.mainView.displayFragment(currentFragment, false, EditVGView.FRAG_NAME);
    }

    /*
     * Account information methods
     */

    /**
     * Makes sure the sign up information is valid
     * @param email email to sign up with
     * @param password password to sign up with
     * @return true when email and password are valid
     */
    private boolean validateSignUpInformation(String email, String password) {
        return validateEmail(email) && validatePassword(password);
    }

    /**
     * Validates that it's a valid email
     * @param email the email to check
     * @return true when the email is valid
     */
    private boolean validateEmail(String email) {
        if (email != null && !email.isEmpty()) {
            boolean test = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
            Log.i(VGL, "Test: "+test);
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
        return false;
    }

    /**
     * Validates that it's a valid password
     * @param password the password to check
     * @return true when it's not empty
     */
    private boolean validatePassword(String password) {
        return !password.isEmpty();
    }

    /**
     * Registers new account
     * @param email email for the account
     * @param password password for the account
     */
    private void registerNewAccount(String email, String password) {
        if (validateSignUpInformation(email, password)) {
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            Log.i(MainActivity.VGL, "Account created");
                            assert auth.getCurrentUser() != null;
                            String uid = auth.getCurrentUser().getUid();
                            CloudDataPreservation preservation =
                                    new CloudDataPreservation(this);
                            accountSignIn(email, password);
                            preservation.createUserDoc(uid);
                        } else {
                            Log.i(MainActivity.VGL, "Account not created");
                            String msg = getResources().getString(R.string.try_again_txt);
                            mainView.displayToast(msg);
                        }
                    });
        } else {
            Log.i(MainActivity.VGL, "Account not created");
            String msg = getResources().getString(R.string.try_again_txt);
            mainView.displayToast(msg);
        }
    }

    /**
     * Sign into the account (as long as one is created)
     * @param email email
     * @param password password
     */
    private void accountSignIn(String email, String password) {
        if (validateEmail(email)) {
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            Log.i(MainActivity.VGL, "Sign in success");
                            String msg = getResources().getString(R.string.success);
                            mainView.displayToast(msg);
                        } else {
                            Log.i(MainActivity.VGL, "Sign in failed");
                            String msg = getResources().getString(R.string.try_again_txt);
                            mainView.displayToast(msg);
                        }
                    });
        } else {
            Log.i(MainActivity.VGL, "Sign in failed");
            String msg = getResources().getString(R.string.try_again_txt);
            mainView.displayToast(msg);
        }
    }

    /**
     * Resets password for the account with the given email
     * @param email email to send the reset email to
     */
    private void accountPWReset(String email) {
        if (validateEmail(email)) {
            auth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Log.i(VGL, "reset email success");
                    String msg = getResources().getString(R.string.pw_reset_email_sent_txt);
                    mainView.displayToast(msg);
                } else {
                    Log.i(VGL, "reset email fail");
                    String msg = getResources().getString(R.string.try_again_txt);
                    mainView.displayToast(msg);
                }
            });
        } else {
            Log.i(VGL, "reset email fail");
            String msg = getResources().getString(R.string.try_again_txt);
            mainView.displayToast(msg);
        }
    }

    /**
     * Authenticates that the account is valid
     * @param email email for the account
     * @param password password for the account
     * @param purpose reason for auth the account
     */
    private void accountAuth(String email, String password, String purpose) {
        if (validateSignUpInformation(email, password)) {
            FirebaseUser user = auth.getCurrentUser();
            AuthCredential credential = EmailAuthProvider
                    .getCredential(email, password);
            assert user != null;
            user.reauthenticate(credential)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            switch (purpose) {
                                case AccountManagementVGView.CHANGE_PW_PURPOSE_KEY:
                                    ((IAccountManagementVGView) MainActivity.this.currentFragment)
                                            .newPWPopUp();
                                    break;
                                case AccountManagementVGView.DELETE_ACCOUNT_PURPOSE_KEY:
                                    accountDelete();
                                    break;
                            }
                        } else {
                            Log.d(MainActivity.VGL, "Cannot authenticate");
                            String msg = getResources().getString(R.string.try_again_txt);
                            mainView.displayToast(msg);
                        }
                    });
        } else {
            Log.d(MainActivity.VGL, "Cannot authenticate");
            String msg = getResources().getString(R.string.try_again_txt);
            mainView.displayToast(msg);
        }
    }

    /**
     * Changes the password of the current account to the new password
     * @param newPassword new password
     */
    private void accountPWChange(String newPassword) {
        FirebaseUser user = auth.getCurrentUser();
        assert user != null;
        if (validatePassword(newPassword)) {
            user.updatePassword(newPassword).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Log.d(MainActivity.VGL, "Password updated");
                    String msg = getResources().getString(R.string.pw_updated_txt);
                    mainView.displayToast(msg);
                } else {
                    Log.d(MainActivity.VGL, "Error password not updated");
                    String msg = getResources().getString(R.string.try_again_txt);
                    mainView.displayToast(msg);
                }
            });
        } else {
            Log.d(MainActivity.VGL, "Error password not updated");
            String msg = getResources().getString(R.string.try_again_txt);
            mainView.displayToast(msg);
        }
    }

    /**
     * Signs out of the current account
     */
    private void accountSignOut() {
        auth.signOut();
        FirebaseUser user = auth.getCurrentUser();
        if (user == null) {
            Log.i(MainActivity.VGL, "sign out successful");
            String msg = getResources().getString(R.string.success);
            mainView.displayToast(msg);
        } else {
            String msg = getResources().getString(R.string.try_again_txt);
            mainView.displayToast(msg);
        }
    }

    /**
     * Deletes the current account
     */
    private void accountDelete() {
        FirebaseUser user = auth.getCurrentUser();
        assert user != null;
        String uid = user.getUid();

        CloudDataPreservation preservation = new CloudDataPreservation(this);
        preservation.deleteUserCollection(uid, (new Timestamp(new Date())));
        preservation.deleteUserDoc(uid);

        user.delete()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d(MainActivity.VGL, "User account deleted.");
                        String msg = getResources().getString(R.string.account_deleted_txt);
                        mainView.displayToast(msg);
                    } else {
                        String msg = getResources().getString(R.string.try_again_txt);
                        mainView.displayToast(msg);
                    }
                });
    }

    /*
     * LISTENER METHODS
     */

    /**
     * Restores the fragment in MainActivity
     * @param curFragment fragment to restore
     */
    @Override
    public void restoreAboutFragment(Fragment curFragment) {
        this.currentFragment = curFragment;
    }

    @Override
    public void restoreAccountManagementFrag(Fragment curFragment) {
        this.currentFragment = curFragment;
    }

    /**
     * Restores the fragment in MainActivity
     * @param curFragment fragment to restore
     */
    @Override
    public void restoreDisplayFragment(Fragment curFragment) {
        this.currentFragment = curFragment;
    }

    /**
     * Restores the fragment in MainActivity
     * @param curFragment fragment to restore
     */
    @Override
    public void restoreEditFragment(Fragment curFragment) {
        this.currentFragment = curFragment;
    }

    /**
     * Alerts listener to return to display fragment
     * (response)
     */
    @Override
    public void onReturnToDisplay() {
        showDisplayFrag(this.consoleOrganizer.getConsoleList().get(0));
    }

    /**
     * Alerts listener to wanting to switch console viewed
     * (response)
     *
     * @param console new console to view
     * @param scrollLeft position in scroll of the console
     */
    @Override
    public void onSwitchConsole(String console, int scrollLeft) {
        showDisplayFrag(console, scrollLeft);
    }

    /**
     * Alerts listener to toggling favorite
     * (response)
     *
     * @param videoGame game to change favorite
     */
    @Override
    public void onFavorite(VideoGame videoGame) {
        videoGame.switchFavorite();
        this.consoleOrganizer.editGame(videoGame);
    }

    /**
     * Alerts listener to the video game needing editing
     * (response)
     *
     * @param videoGame that needs to be updated
     */
    @Override
    public void onEditGame(VideoGame videoGame) {
        showEditFrag(videoGame);
    }

    /**
     * Alerts listener to the video game needing deleting
     * (response)
     *
     * @param videoGame that needs to be deleted
     * @param curConsole current displayed console list
     */
    @Override
    public void onDeleteGame(VideoGame videoGame, String curConsole) {
        int index = this.consoleOrganizer.getGameIndex(videoGame, curConsole);
        this.consoleOrganizer.deleteGame(videoGame);
        Log.i("vgl", "delete");
        ((IDisplayVGView) this.currentFragment).updateDeletedItem(index);
    }

    /**
     * Alerts listener to adding a new game
     * (response)
     */
    @Override
    public void onAddNewGame() {
        showEditFrag();
    }

    /**
     * Alerts listener to show the about page
     * (response)
     */
    public void onDisplayAboutPage() {
        showAboutFrag();
    }

    /**
     * Alerts listener to saving on device
     * (response)
     */
    @Override
    public boolean onDeviceSave() {
        return saveLocally();
    }

    /**
     * Alerts listener to loading from device
     * (response)
     */
    @Override
    public void onDeviceLoad() {
        loadLocally();
        if(!this.consoleOrganizer.getConsoleList().isEmpty()) {
            showDisplayFrag(this.consoleOrganizer.getConsoleList().get(0));
        } else {
            showDisplayFrag(null);
        }
    }

    /**
     * Alerts listener to saving to cloud
     * (response)
     */
    @Override
    public boolean onCloudSave() {
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            Log.i(MainActivity.VGL, "signed in");
            String uid = user.getUid();
            CloudDataPreservation preservation = new CloudDataPreservation(this);
            preservation.saveConsoleOrganizer(this.consoleOrganizer, uid);
        } else {
            String msg = getResources().getString(R.string.try_again_txt);
            mainView.displayToast(msg);
        }
        return true;
    }

    /**
     * Alerts listener to loading from cloud
     * (response)
     */
    @Override
    public boolean onCloudLoad() {
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            Log.i(MainActivity.VGL, "signed in");
            String uid = user.getUid();
            CloudDataPreservation preservation = new CloudDataPreservation(this);
            preservation.loadConsoleOrganizer(uid);
        } else {
            String msg = getResources().getString(R.string.try_again_txt);
            mainView.displayToast(msg);
        }
        return true;
    }

    /**
     * Alerts listener to show the account management page
     * (response)
     */
    @Override
    public void onDisplayAMPage() {
        showAccountManagementFrag();
    }

    /**
     * Alerts listener to submitting the video game
     * (response)
     *
     * @param videoGame the video game to edit/create
     */
    @Override
    public void submitGame(VideoGame videoGame) {
        this.consoleOrganizer.editGame(videoGame);
        showDisplayFrag(videoGame.getConsoles().get(0));
    }

    /**
     * Alerts listener to return to display fragment
     */
    @Override
    public void onEditReturnToDisplay() {
        try {
            showDisplayFrag(this.consoleOrganizer.getConsoleList().get(0));
        } catch (Exception e) {
            showDisplayFrag(null);
        }
    }

    /**
     * Alerts listener to submitting the video game
     * (response)
     *
     * @param consoleName name of the new console
     */
    @Override
    public void onSubmitNewConsole(String consoleName) {
        String upperConsoleName = consoleName.toUpperCase();
        ((IEditVVGView) this.currentFragment).showNewConsole(upperConsoleName);
    }

    /**
     * Alerts listener to sign into account button
     * (response)
     *
     * @param email email to save
     * @param password password to save
     */
    @Override
    public void onSignIn(String email, String password, String purpose) {
        switch (purpose) {
            case AccountManagementVGView.SIGN_UP_PURPOSE_KEY:
                registerNewAccount(email, password);
                break;
            case AccountManagementVGView.SIGN_IN_PURPOSE_KEY:
                accountSignIn(email, password);
                break;
            case AccountManagementVGView.CHANGE_PW_PURPOSE_KEY:
            case AccountManagementVGView.DELETE_ACCOUNT_PURPOSE_KEY:
                accountAuth(email, password, purpose);
                break;
            case AccountManagementVGView.NEW_PW_PURPOSE_KEY:
                accountPWChange(password);
                break;
            case AccountManagementVGView.RESET_PW_PURPOSE_KEY:
                accountPWReset(email);
                break;
        }
    }

    @Override
    public void onSignOut() {
        accountSignOut();
    }

    @Override
    public void onAMReturn() {
        showDisplayFrag(this.consoleOrganizer.getConsoleList().get(0));
    }

    /**
     * Alerts the listener to successful save
     * (response)
     */
    @Override
    public void onCloudSaveSuccess(Timestamp timestamp) {
        String msg = getResources().getString(R.string.saved_to_cloud);
        mainView.displayToast(msg);
    }

    /**
     * Alerts the listener to successful load
     * (response)
     */
    @Override
    public void onCloudLoadSuccess(ConsoleOrganizer consoleOrganizer) {
        this.consoleOrganizer = consoleOrganizer;
        String msg = getResources().getString(R.string.loaded_from_cloud);
        mainView.displayToast(msg);
        if(!this.consoleOrganizer.getConsoleList().isEmpty()) {
            showDisplayFrag(this.consoleOrganizer.getConsoleList().get(0));
        } else {
            showDisplayFrag(null);
        }
    }

    /**
     * Alerts the listener to cloud failure
     * (response)
     */
    @Override
    public void onCloudFailure() {
        String msg = getResources().getString(R.string.try_again_txt);
        mainView.displayToast(msg);
    }
}