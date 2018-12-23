package bgroseclose.leagueofyou.Components

import bgroseclose.leagueofyou.Fragments.DashboardFragment
import bgroseclose.leagueofyou.Modules.DashboardFragmentModule
import bgroseclose.leagueofyou.Modules.PicassoModule
import com.squareup.picasso.Picasso
import dagger.Component

@Component(modules = [DashboardFragmentModule::class, PicassoModule::class])
interface DashboardFragmentComponent {
    fun inject(dashboardFragment: DashboardFragment)
    fun inject(picasso: Picasso)
}