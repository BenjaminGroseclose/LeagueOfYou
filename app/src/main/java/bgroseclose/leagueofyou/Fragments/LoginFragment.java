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
import android.widget.Switch;

import bgroseclose.leagueofyou.Presenters.LoginFragmentPresenter;
import bgroseclose.leagueofyou.R;

public class LoginFragment extends Fragment implements LoginFragmentPresenter.View {

    private LoginFragmentPresenter presenter;
    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private Switch mSaveUsernameToggle;
    private Button mLogin;
    private String username, password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        presenter = new LoginFragmentPresenter(this);

        initViews(rootView);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = mUsernameEditText.getText().toString();
                password = mPasswordEditText.getText().toString();
                presenter.loginUser(username, password);
            }
        });

        return rootView;
    }

    private void initViews(View rootView) {
        mUsernameEditText = rootView.findViewById(R.id.login_username);
        mPasswordEditText = rootView.findViewById(R.id.login_password);
        mSaveUsernameToggle = rootView.findViewById(R.id.login_save_username_toggle);
        mLogin = rootView.findViewById(R.id.login_button);
    }

    @Override
    public void blankUsernameOrPassword() {
        
    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void loginFailed() {

    }
}
