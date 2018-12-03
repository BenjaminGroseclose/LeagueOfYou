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
import android.widget.Toast;

import java.util.LinkedHashMap;
import java.util.Objects;

import bgroseclose.leagueofyou.Adapters.ChampionListAdapter;
import bgroseclose.leagueofyou.Components.DaggerStaticLeagueComponent;
import bgroseclose.leagueofyou.Components.StaticLeagueComponent;
import bgroseclose.leagueofyou.LeagueOfYouSingleton;
import bgroseclose.leagueofyou.Models.ChampionModels.ChampionsModel;
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

    private LinkedHashMap<String, ChampionsModel> championList = null;
    private ChampionListPresenter presenter;
    private StaticLeagueComponent staticLeagueComponent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_champion_list, container, false);
        ButterKnife.bind(this, rootView);

        Toolbar toolbar = Objects.requireNonNull(getActivity()).findViewById(R.id.dashboard_toolbar);
        toolbar.setTitle(getString(R.string.champions));

        staticLeagueComponent = DaggerStaticLeagueComponent.builder()
                .contextModule(new ContextModule(getContext()))
                .build();

        presenter = new ChampionListPresenter(this);

        championListSearchEdit.addTextChangedListener(searchTextWatcher());
        championListSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchChampionList(championListSearchEdit.getText().toString());
            }
        });
        presenter.getChampionList();

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
                searchChampionList(s.toString());
            }
        };
    }

    private void searchChampionList(String text) {
        if(!text.equals("")) {
            LinkedHashMap<String, ChampionsModel> tempChampions = new LinkedHashMap<>();
            for (ChampionsModel championModel : championList.values()) {
                if (championModel.getName().contains(text)) {
                    tempChampions.put(championModel.getKey(), championModel);
                }
            }
            setAdapter(tempChampions);
        } else {
            Toast.makeText(getContext(), "Please enter text before you search.", Toast.LENGTH_SHORT).show();
        }
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
    public void setAdapter(LinkedHashMap<String, ChampionsModel> championList) {
        if(this.championList == null) {
            this.championList = championList;
        }
        ChampionListAdapter adapter = new ChampionListAdapter(getContext(), championList, staticLeagueComponent.getPicasso());
        championRecyclerView.setAdapter(adapter);
        championRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
