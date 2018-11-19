package bgroseclose.leagueofyou.Models.ChampionModels;

import java.util.HashMap;

public class ChampionList {

    private HashMap<String, ChampionListModel> championList;

    public HashMap<String, ChampionListModel> getChampionList() {
        return championList;
    }

    public void setChampionList(HashMap<String, ChampionListModel> championList) {
        this.championList = championList;
    }
}
