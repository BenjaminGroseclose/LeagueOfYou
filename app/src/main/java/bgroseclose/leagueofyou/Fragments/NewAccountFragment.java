package bgroseclose.leagueofyou.Fragments;

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

import bgroseclose.leagueofyou.Models.NewAccount;
import bgroseclose.leagueofyou.Presenters.NewAccountPresenter;
import bgroseclose.leagueofyou.R;

public class NewAccountFragment extends Fragment implements NewAccountPresenter.View {

    private EditText mSummoners;
    private EditText mUsername;
    private EditText mPassword;
    private EditText mConfirmPassword;
    private Button mCreateNewAccount;
    private TextView mDateOfBirth;
    private NewAccountPresenter presenter;
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
                presenter.createNewAccount(setNewAccount());
            }
        });

        return rootView;
    }

    private NewAccount setNewAccount() {
        NewAccount account = null;
        if(isInputValid() && passwordMatch()) {
            account = new NewAccount(summonersName, username, password, dateOfBirth);
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

}
