package bgroseclose.leagueofyou.Presenters.Activities

import bgroseclose.leagueofyou.Activites.ChampionActivity
import bgroseclose.leagueofyou.LeagueOfYouSingleton
import bgroseclose.leagueofyou.Models.ChampionModels.Champion
import bgroseclose.leagueofyou.Retrofit.IStaticLeagueClient

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChampionPresenter(val view: ChampionView, private val staticClient: IStaticLeagueClient ) {

    init {

    }

    fun getChampion(championName: String) {
        view.loadChampion(true)
        val call = staticClient.getChampion(LeagueOfYouSingleton.getCurrentVersionNumber(), championName)
        call.enqueue(object: Callback<Champion> {
            override fun onResponse(call: Call<Champion>, response: Response<Champion>) {
                response.body()?.let {
                    ChampionActivity.champion = it
                }
                view.loadChampion(false)
            }
            override fun onFailure(call: Call<Champion>, t: Throwable) {
                view.loadChampion(false)
                view.displayServerError()
            }
        })
    }

    interface ChampionView {
        fun loadChampion(isVisible: Boolean)
        fun setViewPager()
        fun displayServerError()
    }
}