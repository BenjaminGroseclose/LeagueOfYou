package bgroseclose.leagueofyou.Presenters.Fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.util.Patterns;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;
import java.util.regex.Pattern;

import bgroseclose.leagueofyou.Database.DatabaseClient;
import bgroseclose.leagueofyou.Models.Account;
import bgroseclose.leagueofyou.Models.SummonerInfo;
import bgroseclose.leagueofyou.Retrofit.IRiotClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewAccountPresenter {

    private FirebaseAuth auth;
    private View view;
    private Account account;
    private String username, password;
    private IRiotClient riotClient;

    public NewAccountPresenter(View view, IRiotClient riotClient) {
        this.view = view;
        this.riotClient = riotClient;
        auth = FirebaseAuth.getInstance();
    }

    public void newAccount(Account account, String username, String password) {
        if(account != null) {
            this.username = username;
            this.password = password;
            this.account = account;
            if (validateUsername(username) &&
                    validatePassword(password) &&
                    validateDateOfBirth(account.getDateOfBirth())) {
                startCreateAccount();
            }
        } else {

        }
    }

    private boolean validateUsername(String username) {
        if (Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
            return true;
        } else {
            view.invalidUsername();
            return false;
        }
    }

    private boolean validatePassword(String password) {
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{5,}";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        if (pattern.matcher(password).matches()) {
            return true;
        } else {
            view.invalidPassword();
            return false;
        }
    }

    private void startCreateAccount() {
        if(view.checkConnection()) {
            Call<SummonerInfo> call = riotClient.getSummonersInfo(account.getSummonerName());
            view.progressDialog(true);

            call.enqueue(new Callback<SummonerInfo>() {
                @Override
                public void onResponse(@NonNull Call<SummonerInfo> call, @NonNull Response<SummonerInfo> response) {
                    if (response.body() != null) {
                        account.setSummonerInfo(response.body());
                        createFirebaseAccount();
                    } else {
                        view.progressDialog(false);
                        view.invalidSummonersName();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<SummonerInfo> call, @NonNull Throwable t) {
                    view.progressDialog(false);
                    view.displayServerError();

                }
            });
        }
    }

    private void createFirebaseAccount() {
        auth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            view.progressDialog(false);
                            FirebaseUser user = auth.getCurrentUser();
                            DatabaseClient.saveAccount(user.getUid(), account);
                            if (DatabaseClient.isSuccessful()) {
                                view.returnToLogin(username);
                            } else {
                                view.displayServerError();
                            }
                        } else {
                            view.progressDialog(false);
                            view.displayServerError();
                        }
                    }
                });
    }

    private boolean validateDateOfBirth(Calendar dateOfBirth) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -13);
        if (dateOfBirth.before(cal)) {
            return true;
        } else {
            view.invalidDateOfBirth();
            return false;
        }
    }


    public interface View {
        boolean checkConnection();
        void progressDialog(boolean toDisplay);
        void invalidSummonersName();
        void invalidUsername();
        void invalidPassword();
        void invalidDateOfBirth();
        void displayServerError();
        void returnToLogin(String username);
    }
}
