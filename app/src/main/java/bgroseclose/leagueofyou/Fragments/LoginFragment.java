package bgroseclose.leagueofyou.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bgroseclose.leagueofyou.Presenters.LoginFragmentPresenter;
import bgroseclose.leagueofyou.R;

public class LoginFragment extends Fragment implements LoginFragmentPresenter.View {

    private LoginFragmentPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        presenter = new LoginFragmentPresenter(this);

        return rootView;
    }
}