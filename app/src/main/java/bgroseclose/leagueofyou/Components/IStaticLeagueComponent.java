package bgroseclose.leagueofyou.Components;

import bgroseclose.leagueofyou.Modules.StaticLeagueServiceModule;
import bgroseclose.leagueofyou.Retrofit.IStaticLeagueClient;
import dagger.Component;

@Component(modules = StaticLeagueServiceModule.class)
public interface IStaticLeagueComponent {

    IStaticLeagueClient getStaticLeagueClient();

}
