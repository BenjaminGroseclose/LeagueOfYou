package bgroseclose.leagueofyou.Models.ChampionModels;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ChampionList {

    @SerializedName("version")
    private String version;
    @SerializedName("data")
    private ArrayList<Champion> championList;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ArrayList<Champion> getChampionList() {
        return championList;
    }

    public void setChampionList(ArrayList<Champion> championList) {
        this.championList = championList;
    }
}
