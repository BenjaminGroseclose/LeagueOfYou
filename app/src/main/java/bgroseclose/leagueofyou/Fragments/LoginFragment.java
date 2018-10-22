package bgroseclose.leagueofyou.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import bgroseclose.leagueofyou.Activites.DashboardActivity;
import bgroseclose.leagueofyou.Presenters.Fragments.LoginPresenter;
import bgroseclose.leagueofyou.R;

public class LoginFragment extends Fragment implements LoginPresenter.View {

    private LoginPresenter presenter;
    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private Switch mSaveUsernameToggle;
    private Button mLogin;
    private boolean isSavedUsernameToggled;
    private String username, password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        presenter = new LoginPresenter(this, getActivity());

        initViews(rootView);
        getSharedPref();
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = mUsernameEditText.getText().toString();
                password = mPasswordEditText.getText().toString();
                presenter.loginUser(username, password, mSaveUsernameToggle.isChecked());
            }
        });

        return rootView;
    }

    private void getSharedPref() {
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        username = sharedPref.getString(getString(R.string.save_username), "");
        isSavedUsernameToggled = sharedPref.getBoolean(getString(R.string.save_username_toggle), false);
        mSaveUsernameToggle.setChecked(isSavedUsernameToggled);
    }

    private void initViews(View rootView) {
        mUsernameEditText = rootView.findViewById(R.id.login_username);
        mPasswordEditText = rootView.findViewById(R.id.login_password);
        mSaveUsernameToggle = rootView.findViewById(R.id.login_save_username_toggle);
        mLogin = rootView.findViewById(R.id.login_button);

        Bundle bundle = this.getArguments();
        if(bundle != null) {
            String usernameFromNewAccount = bundle.getString(getContext().getString(R.string.username), "");
            mUsernameEditText.setText(usernameFromNewAccount);
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
    public void invalidUsernameOrPassword() {
        displayAlertDialog(
                getString(R.string.login_failed_title),
                getString(R.string.invalid_username_password_message));
    }

    @Override
    public void loginSuccess() {
        Intent intent = new Intent(getActivity(), DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void loginFailed() {
        displayAlertDialog(
                getString(R.string.login_failed_title),
                getString(R.string.login_failed_message));
    }

    @Override
    public void saveUsername(String username) {
        SharedPreferences.Editor prefs = getActivity().getPreferences(Context.MODE_PRIVATE).edit();
        prefs.putBoolean(getString(R.string.save_username_toggle), true);
        prefs.putString(getString(R.string.save_username), username);
        prefs.apply();
    }

    @Override
    public void unsaveUsername() {
        SharedPreferences.Editor prefs = getActivity().getPreferences(Context.MODE_PRIVATE).edit();
        prefs.putBoolean(getString(R.string.save_username_toggle), false);
        prefs.putString(getString(R.string.save_username), "");
        prefs.apply();
    }
}
