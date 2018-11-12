package bgroseclose.leagueofyou.Retrofit;

import java.util.ArrayList;

import bgroseclose.leagueofyou.Models.ChampionModels.ChampionList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IStaticLeagueClient {

    //http://ddragon.leagueoflegends.com/cdn/6.24.1/data/en_US/champion.json
    @GET("cdn/6.24.1/data/en_US/champion.json")
    Call<ChampionList> getChampionList();

    //https://ddragon.leagueoflegends.com/api/versions.json
    @GET("api/versions.json")
    Call<ArrayList<String>> getVersions();

}
