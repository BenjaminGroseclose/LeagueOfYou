package bgroseclose.leagueofyou.Retrofit;

import bgroseclose.leagueofyou.LeagueOfYouSingleton;
import bgroseclose.leagueofyou.Models.SummonerInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface IRiotClient {

    //   https://na1.api.riotgames.com/lol/summoner/v3/summoners/by-name/RiotSchmick?api_key=
    @GET("https://na1.api.riotgames.com/lol/summoner/v3/summoners/by-name/{summonerName}?api_key=" + LeagueOfYouSingleton.riotKet)
    Call<SummonerInfo> getSummonersInfo (
            @Path("summonerName") String summonerName
    );

}
