package bgroseclose.leagueofyou.Retrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IStaticLeagueClient {
    //https://ddragon.leagueoflegends.com/api/versions.json
    @GET("api/versions.json")
    Call<ArrayList<String>> getVersions();

}
