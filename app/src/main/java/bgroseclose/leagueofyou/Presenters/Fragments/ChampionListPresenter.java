package bgroseclose.leagueofyou.Presenters.Fragments;

import android.support.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import bgroseclose.leagueofyou.Models.ChampionModels.Champion;
import bgroseclose.leagueofyou.Models.ChampionModels.ChampionList;
import bgroseclose.leagueofyou.Retrofit.IStaticLeagueClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChampionListPresenter {

    private View view;
    private IStaticLeagueClient staticLeagueClient;
    private Picasso picasso;

    public ChampionListPresenter(View view, IStaticLeagueClient staticLeagueClient, Picasso picasso) {
        this.staticLeagueClient = staticLeagueClient;
        this.view = view;
        this.picasso = picasso;
    }

    public void getChampionList() {
        if(view.checkConnection()) {
            Call<ChampionList> call = staticLeagueClient.getChampionList();
            view.loadChampions(true);

            call.enqueue(new Callback<ChampionList>() {
                @Override
                public void onResponse(@NonNull Call<ChampionList> call, @NonNull Response<ChampionList> response) {
                    if(response.body() != null) {
                        view.setListAdapter(response.body().getChampionList());
                    } else {
                        view.displayServerError();
                    }
                    view.loadChampions(false);
                }
                @Override
                public void onFailure(@NonNull Call<ChampionList> call, @NonNull Throwable t) {
                    view.loadChampions(false);
                    view.displayServerError();
                }
            });
        }
    }

    public interface View {
        boolean checkConnection();
        void loadChampions(boolean isVisible);
        void setListAdapter(ArrayList<Champion> champions);
        void displayServerError();
    }
}
