package bgroseclose.leagueofyou.Components

import bgroseclose.leagueofyou.Activites.ChampionActivity
import bgroseclose.leagueofyou.Modules.ChampionModule
import bgroseclose.leagueofyou.Modules.PicassoModule
import com.squareup.picasso.Picasso
import dagger.Component

@Component(modules = [ChampionModule::class])
interface ChampionComponent {

    fun inject(championActivity: ChampionActivity)

}