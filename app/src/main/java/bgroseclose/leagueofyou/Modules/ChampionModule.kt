package bgroseclose.leagueofyou.Modules

import bgroseclose.leagueofyou.Presenters.Activities.ChampionPresenter
import bgroseclose.leagueofyou.Retrofit.IStaticLeagueClient
import dagger.Module
import dagger.Provides

@Module(includes = [StaticLeagueServiceModule::class])
class ChampionModule(val view: ChampionPresenter.ChampionView) {

    @Provides
    fun championPresnter(staticClient: IStaticLeagueClient): ChampionPresenter {
        return ChampionPresenter(view, staticClient)
    }

}