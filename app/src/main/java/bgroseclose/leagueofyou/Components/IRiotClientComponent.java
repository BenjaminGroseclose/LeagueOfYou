package bgroseclose.leagueofyou.Components;

import bgroseclose.leagueofyou.Modules.RiotServiceModule;
import bgroseclose.leagueofyou.Modules.StaticLeagueServiceModule;
import bgroseclose.leagueofyou.Retrofit.IRiotClient;
import bgroseclose.leagueofyou.Retrofit.IStaticLeagueClient;
import dagger.Component;

@Component(modules = RiotServiceModule.class)
public interface IRiotClientComponent {

    IRiotClient getRiotClient();

}
