package bgroseclose.leagueofyou.Retrofit;

import bgroseclose.leagueofyou.Models.ChampionList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IStaticLeagueClient {

    //http://ddragon.leagueoflegends.com/cdn/6.24.1/data/en_US/champion.json

    @GET("cdn/6.24.1/data/en_US/champion.json")
    Call<ChampionList> getChampionList();

}
