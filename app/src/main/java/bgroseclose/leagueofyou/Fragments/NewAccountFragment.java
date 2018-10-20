package bgroseclose.leagueofyou.Fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import bgroseclose.leagueofyou.Models.Account;
import bgroseclose.leagueofyou.Presenters.Fragments.NewAccountPresenter;
import bgroseclose.leagueofyou.R;

public class NewAccountFragment extends Fragment implements NewAccountPresenter.View {

    private EditText mSummoners;
    private EditText mUsername;
    private EditText mPassword;
    private EditText mConfirmPassword;
    private Button mCreateNewAccount;
    private TextView mDateOfBirth;
    private NewAccountPresenter presenter;
    private ProgressDialog progressDialog;
    String summonersName, username, password, confirmPasword, dateOfBirth;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new_account, container, false);

        presenter = new NewAccountPresenter(this);
        initView(rootView);

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
        return password.equals(confirmPasword);
    }

    private boolean isInputValid() {
        return (
                !(summonersName = mSummoners.getText().toString()).isEmpty() &&
                        !(username = mUsername.getText().toString()).isEmpty() &&
                        !(password = mPassword.getText().toString()).isEmpty() &&
                        !(confirmPasword = mConfirmPassword.getText().toString()).isEmpty() &&
                        !(dateOfBirth = mDateOfBirth.getText().toString()).isEmpty()
        );
    }

    private void initView(View view) {
        mSummoners = view.findViewById(R.id.new_account_summoners_name);
        mUsername = view.findViewById(R.id.new_account_username);
        mPassword = view.findViewById(R.id.new_account_password);
        mConfirmPassword = view.findViewById(R.id.new_account_password_confirm);
        mCreateNewAccount = view.findViewById(R.id.create_new_account);
        mDateOfBirth = view.findViewById(R.id.new_account_dob);
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
    public void accountExists() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle(getString(R.string.account_exists_title))
                .setMessage(getString(R.string.account_exists_message))
                .setIcon(R.drawable.ic_alert)
                .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.login_container, new LoginFragment())
                                .addToBackStack(null)
                                .commit();
                    }
                })
                .setNeutralButton(getString(R.string.cancel), null);

        dialog.create();
        dialog.show();
    }
}
