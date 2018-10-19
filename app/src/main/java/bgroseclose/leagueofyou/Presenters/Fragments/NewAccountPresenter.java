package bgroseclose.leagueofyou.Presenters.Fragments;

import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Patterns;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.Calendar;
import java.util.regex.Pattern;

import bgroseclose.leagueofyou.LeagueOfYouSingleton;
import bgroseclose.leagueofyou.Models.NewAccount;
import bgroseclose.leagueofyou.Models.SummonerInfo;
import bgroseclose.leagueofyou.Retrofit.RiotClient;
import bgroseclose.leagueofyou.Retrofit.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewAccountPresenter {

    private FirebaseAuth auth;
    private View view;
    private NewAccount account;

    public NewAccountPresenter(View view) {
        this.view = view;
    }

    public void newAccount(NewAccount account) {
        this.account = account;
        auth = FirebaseAuth.getInstance();
        startCreateAccount();
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
        RiotClient client = ServiceGenerator.createService(RiotClient.class);
        Call<SummonerInfo> call = client.getSummonersInfo(account.getSummonerName());
        view.progressDialog(true);

        call.enqueue(new Callback<SummonerInfo>() {
            @Override
            public void onResponse(Call<SummonerInfo> call, Response<SummonerInfo> response) {
                if (response.body() != null) {
                    account.setSummonerInfo(response.body());
                    createNewAccount();
                } else {
                    view.invalidSummonersName();
                }
            }
            @Override
            public void onFailure(Call<SummonerInfo> call, Throwable t) {
                view.progressDialog(false);
                view.displayServerError();

            }
        });
    }

    private void createNewAccount() {
        if (validateUsername(account.getUsername()) &&
                validatePassword(account.getPassword()) &&
                validateDateOfBirth(account.getDateOfBirth())) {
            createFirebaseAccount();
        }
    }

    private boolean doesAccountExist() {

        return false;
    }

    private void createFirebaseAccount() {
        if(doesAccountExist()) {
            auth.createUserWithEmailAndPassword(account.getUsername(), account.getPassword())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                view.progressDialog(false);
                            } else {
                                view.progressDialog(false);
                                view.displayServerError();
                            }
                        }
                    });
        } else {
            view.progressDialog(false);
            view.accountExists();
        }

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
        void progressDialog(boolean toDisplay);
        void invalidSummonersName();
        void invalidUsername();
        void invalidPassword();
        void invalidDateOfBirth();
        void displayServerError();
        void accountExists();
    }
}
