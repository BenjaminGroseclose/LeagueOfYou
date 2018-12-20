package bgroseclose.leagueofyou.Fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import bgroseclose.leagueofyou.Activites.DashboardActivity;
import bgroseclose.leagueofyou.Activites.LoginActivity;
import bgroseclose.leagueofyou.Components.DaggerStaticLeagueComponent;
import bgroseclose.leagueofyou.Components.StaticLeagueComponent;
import bgroseclose.leagueofyou.Modules.ContextModule;
import bgroseclose.leagueofyou.Presenters.Fragments.LoginPresenter;
import bgroseclose.leagueofyou.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginFragment extends Fragment implements LoginPresenter.View {


    @BindView(R.id.login_username) EditText mUsernameEditText;
    @BindView(R.id.login_password) EditText mPasswordEditText;
    @BindView(R.id.login_save_username_toggle) Switch mSaveUsernameToggle;
    @BindView(R.id.login_button) Button mLogin;
    @BindView(R.id.new_account_text) TextView mNewAccountTextView;
    private boolean isSavedUsernameToggled;
    private String username, password;
    private LoginPresenter presenter;
    private StaticLeagueComponent staticLeagueComponent;
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, rootView);

        staticLeagueComponent = DaggerStaticLeagueComponent.builder()
                .contextModule(new ContextModule(getContext()))
                .build();

        Toolbar toolbar = getActivity().findViewById(R.id.login_toolbar);
        toolbar.setTitle(getString(R.string.login));

        presenter = new LoginPresenter(this, getActivity(), staticLeagueComponent.getStaticLeagueClient());

        getSharedPref();
        Bundle bundle = this.getArguments();
        if(bundle != null) {
            String usernameFromNewAccount = bundle.getString(getContext().getString(R.string.username), "");
            mUsernameEditText.setText(usernameFromNewAccount);
        }

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = mUsernameEditText.getText().toString();
                password = mPasswordEditText.getText().toString();
                presenter.loginUser(username, password, mSaveUsernameToggle.isChecked());
            }
        });

        mNewAccountTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.existingFragment = new NewAccountFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.login_container, LoginActivity.existingFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return rootView;
    }

    private void getSharedPref() {
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        username = sharedPref.getString(getString(R.string.save_username), "");
        isSavedUsernameToggled = sharedPref.getBoolean(getString(R.string.save_username_toggle), false);
        mSaveUsernameToggle.setChecked(isSavedUsernameToggled);
        mUsernameEditText.setText(username);
    }

    private void displayAlertDialog(String title, String message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle(title)
                .setMessage(message)
                .setIcon(R.drawable.ic_alert)
                .setCancelable(false)
                .setNeutralButton(getString(R.string.ok), null);
        dialog.create();
        dialog.show();
    }

    @Override
    public void progressDialog(boolean isVisible) {
        if (isVisible) {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage(getString(R.string.logging_in));
            progressDialog.setCancelable(false);
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
    }

    @Override
    public void invalidUsernameOrPassword() {
        displayAlertDialog(
                getString(R.string.login_failed_title),
                getString(R.string.invalid_username_password_message));
    }

    @Override
    public void displayServerError() {
        displayAlertDialog(
                getString(R.string.server_error_title),
                getString(R.string.server_error_message));
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
