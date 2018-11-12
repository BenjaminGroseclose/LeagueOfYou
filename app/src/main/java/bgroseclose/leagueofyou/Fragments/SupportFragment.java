package bgroseclose.leagueofyou.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bgroseclose.leagueofyou.R;

public class SupportFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Toolbar toolbar = getActivity().findViewById(R.id.login_toolbar);
        toolbar.setTitle(getString(R.string.support));
        return inflater.inflate(R.layout.fragment_support, container, false);
    }
}
