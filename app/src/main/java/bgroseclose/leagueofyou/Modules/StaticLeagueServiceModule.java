package bgroseclose.leagueofyou.Modules;

import javax.inject.Named;

import bgroseclose.leagueofyou.LeagueOfYouSingleton;
import bgroseclose.leagueofyou.Retrofit.IStaticLeagueClient;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = NetworkModule.class)
public class StaticLeagueServiceModule {


    @Provides
    public IStaticLeagueClient riotClient( Retrofit retrofit) {
        return  retrofit.create(IStaticLeagueClient.class);
    }

    @Provides
    public Retrofit staticRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(LeagueOfYouSingleton.riotStaticBaseUrl)
                .build();
    }

}
