package bgroseclose.leagueofyou.Components;

import com.squareup.picasso.Picasso;

import bgroseclose.leagueofyou.Modules.PicassoModule;
import bgroseclose.leagueofyou.Modules.StaticLeagueServiceModule;
import bgroseclose.leagueofyou.Retrofit.IStaticLeagueClient;
import dagger.Component;

@Component(modules = {StaticLeagueServiceModule.class, PicassoModule.class})
public interface StaticLeagueComponent {

    IStaticLeagueClient getStaticLeagueClient();

    Picasso getPicasso();

}
