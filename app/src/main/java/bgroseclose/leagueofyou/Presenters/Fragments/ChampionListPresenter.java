package bgroseclose.leagueofyou.Presenters.Fragments;

import com.google.firebase.database.DataSnapshot;
import java.util.LinkedHashMap;

import bgroseclose.leagueofyou.Database.DatabaseClient;
import bgroseclose.leagueofyou.Database.IDatabaseListener;
public class ChampionListPresenter implements IDatabaseListener {

    private View view;

    public ChampionListPresenter(View view) {
        this.view = view;
    }

    public void getChampionList() {
        if(view.checkConnection()) {
            DatabaseClient.getChampionList(this);
        }
    }

    @Override
    public void onStart() {
        view.loadChampions(true);
    }

    @Override
    public void onSuccess(DataSnapshot snapshot) {
        LinkedHashMap<String, String> championList = new LinkedHashMap<>();
        for(DataSnapshot dataSnapshot: snapshot.getChildren()) {
            championList.put(dataSnapshot.getKey(), dataSnapshot.getValue(String.class));
            view.loadChampions(false);
        }
        view.setAdapter(championList);
    }

    @Override
    public void onFailure() {
        view.loadChampions(false);
        view.displayServerError();
    }

    public interface View {
        boolean checkConnection();
        void loadChampions(boolean isVisible);
        void setAdapter(LinkedHashMap<String, String> championList);
        void displayServerError();
    }
}
