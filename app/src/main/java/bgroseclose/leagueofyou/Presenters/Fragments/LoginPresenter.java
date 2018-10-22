package bgroseclose.leagueofyou.Presenters.Fragments;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import bgroseclose.leagueofyou.Database.DatabaseClient;

public class LoginPresenter {

    private boolean isSaveUsernameToggled;
    private View view;
    private FirebaseAuth auth;
    private Activity activity;
    private String username, password;

    public LoginPresenter(View view, Activity activity) {
        auth = FirebaseAuth.getInstance();
        this.view = view;
        this.activity = activity;
    }

    public void loginUser(String username, String password, boolean isSaveUsernameToggled) {
        this.isSaveUsernameToggled = isSaveUsernameToggled;
        this.username = username;
        this.password = password;
        if (!username.isEmpty() && !password.isEmpty()) {
            attemptLogin();
        } else {
            view.invalidUsernameOrPassword();
        }
    }

    private void attemptLogin() {
        auth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            view.loginSuccess();
                            FirebaseUser user = auth.getCurrentUser();
                            DatabaseClient.getAccount(user.getUid());
                            if (isSaveUsernameToggled) {
                                view.saveUsername(username);
                            } else {
                                view.unsaveUsername();
                            }
                        } else {
                            view.loginFailed();
                        }
                    }
                });
    }

    public interface View {
        void invalidUsernameOrPassword();

        void saveUsername(String username);

        void unsaveUsername();

        void loginSuccess();

        void loginFailed();
    }
}
