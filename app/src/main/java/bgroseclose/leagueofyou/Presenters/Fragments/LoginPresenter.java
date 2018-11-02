package bgroseclose.leagueofyou.Presenters.Fragments;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import bgroseclose.leagueofyou.Database.DatabaseClient;
import bgroseclose.leagueofyou.LeagueOfYouSingleton;
import bgroseclose.leagueofyou.Retrofit.IStaticLeagueClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {

    private boolean isSaveUsernameToggled;
    private View view;
    private FirebaseAuth auth;
    private Activity activity;
    private String username, password;
    private IStaticLeagueClient staticLeagueClient;
    private boolean wasVersionFound;

    public LoginPresenter(View view, Activity activity, IStaticLeagueClient staticLeagueClient) {
        auth = FirebaseAuth.getInstance();
        this.view = view;
        this.activity = activity;
        this.staticLeagueClient = staticLeagueClient;
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
                            getGameVersion();
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

    public boolean getGameVersion() {
        view.progressDialog(true);
        final Call<ArrayList<String>> request = staticLeagueClient.getVersions();
        request.enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                LeagueOfYouSingleton.setCurrentVersionNumber(response.body().get(0));
                wasVersionFound = true;
                view.loginSuccess();
                view.progressDialog(false);
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                view.displayServerError();
                wasVersionFound = false;
                view.progressDialog(false);
            }
        });
        return wasVersionFound;
    }

    public interface View {
        void progressDialog(boolean isVisible);

        void invalidUsernameOrPassword();

        void displayServerError();

        void saveUsername(String username);

        void unsaveUsername();

        void loginSuccess();

        void loginFailed();
    }
}
