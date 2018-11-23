package bgroseclose.leagueofyou.Presenters.Fragments

import bgroseclose.leagueofyou.LeagueOfYouSingleton
import bgroseclose.leagueofyou.Models.ChampionModels.Champion
import bgroseclose.leagueofyou.Retrofit.IStaticLeagueClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChampionPresenter(val staticLeagueClient: IStaticLeagueClient, val view: ChampionView) {

    lateinit var championUrls: ArrayList<String>

    fun getChampionData(name: String) {
        view.progressBar(true)
        val call = staticLeagueClient.getChampion(LeagueOfYouSingleton.getCurrentVersionNumber(), name)
        call.enqueue(object: Callback<Champion>{
            override fun onResponse(call: Call<Champion>, response: Response<Champion>) {
                response?.let {
                    view.setPagerAdater((it.body()!!))
                    view.progressBar(false)
                }
            }

            override fun onFailure(call: Call<Champion>, t: Throwable) {
                view.progressBar(false)
            }
        })
    }

    interface ChampionView {
        fun progressBar(isVisible: Boolean)
        fun displayServerError()
        fun setPagerAdater(champion: Champion)
    }
}