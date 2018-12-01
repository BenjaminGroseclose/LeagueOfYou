package bgroseclose.leagueofyou.Components;

import com.squareup.picasso.Picasso;

import bgroseclose.leagueofyou.Modules.PicassoModule;
import bgroseclose.leagueofyou.Modules.RiotServiceModule;
import bgroseclose.leagueofyou.Modules.StaticLeagueServiceModule;
import bgroseclose.leagueofyou.Retrofit.IRiotClient;
import bgroseclose.leagueofyou.Retrofit.IStaticLeagueClient;
import dagger.Component;

@Component(modules = {RiotServiceModule.class, PicassoModule.class})
public interface ApplicationComponent {

    IRiotClient getRiotClient();

    Picasso getPicasso();

}
