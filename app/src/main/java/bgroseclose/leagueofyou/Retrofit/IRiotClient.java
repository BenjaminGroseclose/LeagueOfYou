package bgroseclose.leagueofyou.Retrofit;

import java.util.List;

import bgroseclose.leagueofyou.LeagueOfYouSingleton;
import bgroseclose.leagueofyou.Models.SummonerInfo;
import bgroseclose.leagueofyou.Models.SummonerRankInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface IRiotClient {

    // base url: "https://na1.api.riotgames.com/lol/"


    //https://na1.api.riotgames.com/lol/summoner/v4/summoners/by-name/
    @GET("summoner/v4/summoners/by-name/{summonerName}?api_key=" + LeagueOfYouSingleton.riotKet)
    Call<SummonerInfo> getSummonersInfo (
            @Path("summonerName") String summonerName
    );

    //https://na1.api.riotgames.com/lol/league/v4/positions/by-summoner/
    @GET("league/v4/positions/by-summoner/{summonerId}?api_key=" + LeagueOfYouSingleton.riotKet)
    Call<List<SummonerRankInfo>> getSummonerRankInfo (
            @Path("summonerId") String summonerId
    );

}
