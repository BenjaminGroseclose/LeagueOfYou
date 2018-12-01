package bgroseclose.leagueofyou.Components

import bgroseclose.leagueofyou.Activites.ChampionActivity
import bgroseclose.leagueofyou.Modules.ChampionModule
import dagger.Component

@Component(modules = [ChampionModule::class])
interface ChampionComponent {

    fun inject(championActivity: ChampionActivity)

}