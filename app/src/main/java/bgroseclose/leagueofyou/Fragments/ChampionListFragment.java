package bgroseclose.leagueofyou.Fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import bgroseclose.leagueofyou.Adapters.ChampionListAdapter;
import bgroseclose.leagueofyou.Components.DaggerIStaticLeagueComponent;
import bgroseclose.leagueofyou.Components.IApplicationComponent;
import bgroseclose.leagueofyou.Components.IStaticLeagueComponent;
import bgroseclose.leagueofyou.LeagueOfYouSingleton;
import bgroseclose.leagueofyou.Models.ChampionModels.Champion;
import bgroseclose.leagueofyou.Models.ChampionModels.ChampionList;
import bgroseclose.leagueofyou.Modules.ContextModule;
import bgroseclose.leagueofyou.Presenters.Fragments.ChampionListPresenter;
import bgroseclose.leagueofyou.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ChampionListFragment extends Fragment implements ChampionListPresenter.View {

    @BindView(R.id.champion_list_search)
    ImageButton championListSearchBtn;
    @BindView(R.id.champion_list_search_edit)
    EditText championListSearchEdit;
    @BindView(R.id.champion_recycler_view)
    RecyclerView championRecyclerView;
    @BindView(R.id.champion_list_progress_bar)
    ProgressBar progressBar;

    private ArrayList<Champion> champions;
    private ChampionListPresenter presenter;
    private IStaticLeagueComponent staticLeagueComponent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_champion_list, container, false);
        ButterKnife.bind(this, rootView);


        Toolbar toolbar = getActivity().findViewById(R.id.dashboard_toolbar);
        toolbar.setTitle(getString(R.string.champions));

        staticLeagueComponent = DaggerIStaticLeagueComponent.builder()
                .contextModule(new ContextModule(getContext()))
                .build();

        presenter = new ChampionListPresenter(this, staticLeagueComponent.getStaticLeagueClient(), staticLeagueComponent.getPicasso());

        presenter.getChampionList();

        championListSearchEdit.addTextChangedListener(searchTextWatcher());

        return rootView;
    }

    private TextWatcher searchTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ArrayList<Champion> tempChampions = null;
                for(Champion champion : champions) {
                    if(champion.getName().contains(s)) {
                        tempChampions.add(champion);
                    }
                }
                setListAdapter(tempChampions);
            }
        };
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
    public void loadChampions(boolean isVisible) {
        if(isVisible) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void displayServerError() {
        displayAlertDialog(
                getString(R.string.server_error_title),
                getString(R.string.server_error_message));
    }

    @Override
    public void setListAdapter(ArrayList<Champion> champions) {
        this.champions = champions;
        ChampionListAdapter adapter = new ChampionListAdapter(this.champions, staticLeagueComponent.getPicasso());
        championRecyclerView.setAdapter(adapter);
        championRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
