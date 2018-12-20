package bgroseclose.leagueofyou.Components

import bgroseclose.leagueofyou.Fragments.ChampionOverviewFragment
import bgroseclose.leagueofyou.Modules.ChampionOverviewModule
import bgroseclose.leagueofyou.Modules.PicassoModule
import com.squareup.picasso.Picasso
import dagger.Component

@Component(modules = [ChampionOverviewModule::class, PicassoModule::class])
interface ChampionOverviewComponent {

    fun inject(championOverviewFragment: ChampionOverviewFragment)

    fun inject(picasso: Picasso)

}