package bgroseclose.leagueofyou.Presenters.Fragments;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

import bgroseclose.leagueofyou.Database.DatabaseClient;
import bgroseclose.leagueofyou.Database.IDatabaseListener;
import bgroseclose.leagueofyou.LeagueOfYouSingleton;
import bgroseclose.leagueofyou.Models.LeagueOfYouAccount;
import bgroseclose.leagueofyou.Retrofit.IStaticLeagueClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements IDatabaseListener {

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
                            DatabaseClient.getAccount(user.getUid(),LoginPresenter.this);
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

    private void getGameVersion() {
        view.progressDialog(true);
        final Call<ArrayList<String>> request = staticLeagueClient.getVersions();
        request.enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                LeagueOfYouSingleton.setCurrentVersionNumber(response.body().get(0));
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                view.displayServerError();
                view.progressDialog(false);
            }
        });
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onSuccess(DataSnapshot snapshot) {
        for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
            view.progressDialog(false);
            LeagueOfYouAccount leagueOfYouAccount = dataSnapshot.getValue(LeagueOfYouAccount.class);
            LeagueOfYouSingleton.setLeagueOfYouAccount(leagueOfYouAccount);
            view.loginSuccess();
        }
    }

    @Override
    public void onFailure() {
        view.displayServerError();
        view.progressDialog(false);
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
