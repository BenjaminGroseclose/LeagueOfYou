package bgroseclose.leagueofyou.Modules

import bgroseclose.leagueofyou.Presenters.Fragments.ChampionOverviewPresenter
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides

@Module
class ChampionOverviewModule {

    @Provides
    fun championOverviewPresenter() : ChampionOverviewPresenter {
        return ChampionOverviewPresenter()
    }
}