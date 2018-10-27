package bgroseclose.leagueofyou.Components;

import bgroseclose.leagueofyou.Modules.RiotServiceModule;
import bgroseclose.leagueofyou.Retrofit.IRiotClient;
import dagger.Component;

@Component(modules = RiotServiceModule.class)
public interface IRiotClientComponent {

    IRiotClient getRiotClient();

}
