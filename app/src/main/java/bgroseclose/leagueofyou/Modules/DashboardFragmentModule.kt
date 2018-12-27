package bgroseclose.leagueofyou.Modules

import bgroseclose.leagueofyou.Presenters.Fragments.DashboardFragmentPresenter
import bgroseclose.leagueofyou.Retrofit.IRiotClient
import dagger.Module
import dagger.Provides

@Module(includes = [RiotServiceModule::class])
class DashboardFragmentModule(val view: DashboardFragmentPresenter.View) {

    @Provides
    fun dashboardFragmentPresnter(riotClient: IRiotClient) : DashboardFragmentPresenter {
        return DashboardFragmentPresenter(view, riotClient)
    }

}