package bgroseclose.leagueofyou.Modules;

import javax.inject.Named;

import bgroseclose.leagueofyou.LeagueOfYouSingleton;
import bgroseclose.leagueofyou.Retrofit.IRiotClient;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = NetworkModule.class)
public class RiotServiceModule {

    @Provides
    public IRiotClient riotClient(Retrofit retrofit) {
        return  retrofit.create(IRiotClient.class);
    }

    @Provides
    public Retrofit riotRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(LeagueOfYouSingleton.riotBaseUrl)
                .build();
    }
}
