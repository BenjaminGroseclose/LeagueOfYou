package bgroseclose.leagueofyou.Modules

import bgroseclose.leagueofyou.Presenters.Fragments.DashboardFragPresenter
import bgroseclose.leagueofyou.Retrofit.IRiotClient
import dagger.Module
import dagger.Provides

@Module(includes = [RiotServiceModule::class])
class DashboardFragmentModule(val view: DashboardFragPresenter.View) {

    @Provides
    fun dashboardFragmentPresnter(riotClient: IRiotClient) : DashboardFragPresenter {
        return DashboardFragPresenter(view, riotClient)
    }

}