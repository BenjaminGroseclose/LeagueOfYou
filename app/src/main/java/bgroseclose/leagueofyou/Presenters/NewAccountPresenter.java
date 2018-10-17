package bgroseclose.leagueofyou.Presenters;

import android.util.Log;
import android.util.Patterns;

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

    private static final String TAG = "NewAccountPresenter";

    private boolean isSummonersNameValid;
    private View view;
    private NewAccount account;

    public NewAccountPresenter(View view) {
        this.view = view;
    }

    public void newAccount(NewAccount account) {
        this.account = account;
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
                view.progressDialog(false);
                if (response.body() != null) {
                    LeagueOfYouSingleton.setSummonerInfo(response.body());
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

    private void createFirebaseAccount() {
        //todo: validate account isn't already created... then create account. S
        //todo: store by summonerId in Firebase
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
