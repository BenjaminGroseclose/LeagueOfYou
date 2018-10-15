package bgroseclose.leagueofyou.Retrofit;

import bgroseclose.leagueofyou.Models.SummonerInfo;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RiotClient {

    //   https://na1.api.riotgames.com/lol/summoner/v3/summoners/by-name/RiotSchmick?api_key=
    @GET("summoner/v3/summoners/by-name/RiotSchmick?api_key=" + ServiceGenerator.Key)
    Call<SummonerInfo> getSummonersInfo (

    );

}
