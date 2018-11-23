package bgroseclose.leagueofyou.Retrofit;

import java.util.ArrayList;

import bgroseclose.leagueofyou.LeagueOfYouSingleton;
import bgroseclose.leagueofyou.Models.ChampionModels.Champion;
import bgroseclose.leagueofyou.Models.SummonerInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IStaticLeagueClient {
    //https://ddragon.leagueoflegends.com/api/versions.json
    @GET("api/versions.json")
    Call<ArrayList<String>> getVersions();

    //http://ddragon.leagueoflegends.com/cdn/6.24.1/data/en_US/champion/Aatrox.json
    @GET("cdn/{versionNumber}/data/en_US/champion/{championName}.json")
    Call<Champion> getChampion(
            @Path("versionNumber") String versionNumber, @Path("championName") String championName
    );
}
