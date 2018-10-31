package bgroseclose.leagueofyou.Fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import bgroseclose.leagueofyou.Components.DaggerIRiotClientComponent;
import bgroseclose.leagueofyou.Components.IRiotClientComponent;
import bgroseclose.leagueofyou.LeagueOfYouSingleton;
import bgroseclose.leagueofyou.Models.Account;
import bgroseclose.leagueofyou.Presenters.Fragments.NewAccountPresenter;
import bgroseclose.leagueofyou.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NewAccountFragment extends Fragment implements NewAccountPresenter.View {

    @BindView(R.id.new_account_summoners_name) EditText mSummoners;
    @BindView(R.id.new_account_username) EditText mUsername;
    @BindView(R.id.new_account_password) EditText mPassword;
    @BindView(R.id.new_account_password_confirm) EditText mConfirmPassword;
    @BindView(R.id.create_new_account) Button mCreateNewAccount;
    @BindView(R.id.new_account_dob) TextView mDateOfBirth;
    private NewAccountPresenter presenter;
    private ProgressDialog progressDialog;
    private String summonersName, username, password, confirmPasword, dateOfBirth;
    private IRiotClientComponent riotClientComponent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new_account, container, false);
        ButterKnife.bind(this, rootView);

        riotClientComponent = DaggerIRiotClientComponent.builder()
                .build();


        presenter = new NewAccountPresenter( this, riotClientComponent.getRiotClient());

        mCreateNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.newAccount(setNewAccount(), username, password);
            }
        });

        return rootView;
    }

    private Account setNewAccount() {
        Account account = null;
        if (isInputValid() && passwordMatch()) {
            account = new Account(summonersName, dateOfBirth);
        }
        return account;
    }

    private boolean passwordMatch() {
        if(password.equals(confirmPasword)) {
            return true;
        } else {
            displayAlertDialog(
                    getString(R.string.password_invalid_title),
                    getString(R.string.password_mismatch_message)
            );
            return false;
        }
    }

    private boolean isInputValid() {
        if(
                !(summonersName = mSummoners.getText().toString()).isEmpty() &&
                        !(username = mUsername.getText().toString()).isEmpty() &&
                        !(password = mPassword.getText().toString()).isEmpty() &&
                        !(confirmPasword = mConfirmPassword.getText().toString()).isEmpty() &&
                        !(dateOfBirth = mDateOfBirth.getText().toString()).isEmpty()
        ) {
            return true;
        } else {
            displayAlertDialog(
                    getString(R.string.missing_input_title),
                    getString(R.string.missing_input_message)
            );
            return false;
        }
    }

    private void displayAlertDialog(String title, String message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle(title)
                .setMessage(message)
                .setIcon(R.drawable.ic_alert)
                .setNeutralButton(getString(R.string.ok), null);
        dialog.create();
        dialog.show();
    }

    @Override
    public boolean checkConnection() {
        if(LeagueOfYouSingleton.checkConnection(getContext())) {
            return true;
        } else {
            displayAlertDialog(
                    getString(R.string.connection_failed_title),
                    getString(R.string.connection_failed_message)
            );
            return false;
        }
    }

    @Override
    public void progressDialog(boolean toDisplay) {
        progressDialog = new ProgressDialog(getContext());
        if (toDisplay) {
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage(getString(R.string.progress_account_message));
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
    }

    @Override
    public void invalidSummonersName() {
        displayAlertDialog(
                getString(R.string.summoners_invalid_title),
                getString(R.string.summoners_invalid_message)
        );
    }

    @Override
    public void invalidUsername() {
        displayAlertDialog(
                getString(R.string.username_invalid_title),
                getString(R.string.username_invalid_message)
        );
    }

    @Override
    public void invalidPassword() {
        displayAlertDialog(
                getString(R.string.password_invalid_title),
                getString(R.string.password_invalid_message)
        );
    }

    @Override
    public void invalidDateOfBirth() {
        displayAlertDialog(
                getString(R.string.dob_invalid_title),
                getString(R.string.dob_invalid_message)
        );
    }

    @Override
    public void displayServerError() {
        displayAlertDialog(
                getString(R.string.server_error_title),
                getString(R.string.server_error_message)
        );
    }

    @Override
    public void returnToLogin(String username) {
            LoginFragment loginFragment = new LoginFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putString(getContext().getString(R.string.username), username);
            loginFragment.setArguments(bundle);
            transaction.replace(R.id.login_container, loginFragment);
    }
}
