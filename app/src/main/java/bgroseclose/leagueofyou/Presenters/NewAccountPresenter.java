package bgroseclose.leagueofyou.Presenters;

import android.util.Patterns;

import java.util.Calendar;
import java.util.regex.Pattern;

import bgroseclose.leagueofyou.Models.NewAccount;

public class NewAccountPresenter {

    private View view;

    public NewAccountPresenter(View view) {
        this.view = view;
    }

    public void createNewAccount(NewAccount account) {
        if(account != null) {

        } else {
            //todo: something went wrong.
        }
    }

    private boolean validateUsername(String username) {
        if(!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
            return true;
        } else {
            view.invalidUsername();
            return false;
        }
    }

    private boolean validatePassword(String password) {
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{5,}";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        if(pattern.matcher(password).matches()) {
            return true;
        } else {
            view.invalidPassword();
            return false;
        }
    }

    private boolean validateSummoner() {
        //todo: implement an api call here.
        return false;
    }

    private boolean validateDateOfBirth(Calendar dateOfBirth) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -13);
        if(dateOfBirth.after(cal)) {
            return true;
        } else {
            return false;
        }
    }

    public interface View {
        void invalidUsername();
        void invalidPassword();
    }

}
