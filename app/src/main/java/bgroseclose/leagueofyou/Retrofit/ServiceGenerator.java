package bgroseclose.leagueofyou.Retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
//   https://na1.api.riotgames.com/lol/summoner/v3/summoners/by-name/RiotSchmick?api_key=

    private static final String BASE_URL = "https://na1.api.riotgames.com/lol/";
    // todo:  key needs to be updated every 24 hours.
    public static final String Key = "RGAPI-4a5da445-4fd5-4d41-8e33-8e784e3cd711";

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    private static OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder();

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
