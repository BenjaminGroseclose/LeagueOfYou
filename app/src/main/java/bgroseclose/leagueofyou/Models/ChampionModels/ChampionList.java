package bgroseclose.leagueofyou.Models.ChampionModels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChampionList {

    @SerializedName("version")
    private String version;
    @SerializedName("data")
    private List<Champion> championList;

}
