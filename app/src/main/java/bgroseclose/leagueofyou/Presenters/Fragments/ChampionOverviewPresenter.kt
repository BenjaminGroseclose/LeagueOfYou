package bgroseclose.leagueofyou.Presenters.Fragments

import bgroseclose.leagueofyou.Models.CounterChampion
import bgroseclose.leagueofyou.Models.CounterData

class ChampionOverviewPresenter() {

    fun getMatchupData() {
       TODO("not implemented -> waiting on API")
    }

    interface View {
        fun progressBar(isVisible: Boolean)
        fun setCounterAdapter(counterChampions: CounterData)
    }

}