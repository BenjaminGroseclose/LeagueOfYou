package bgroseclose.leagueofyou.Presenters.Fragments

import bgroseclose.leagueofyou.Models.CounterChampion

class ChampionOverviewPresenter() {

    fun getMatchupData() {
       TODO("not implemented -> waiting on API")
    }

    interface View {
        fun progressBar(isVisible: Boolean)
        fun setCounterAdapter(counterChampions: List<CounterChampion>)
    }

}