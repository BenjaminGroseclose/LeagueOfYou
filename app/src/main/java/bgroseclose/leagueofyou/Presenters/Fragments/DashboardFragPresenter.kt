package bgroseclose.leagueofyou.Presenters.Fragments

import bgroseclose.leagueofyou.Models.MatchupModels.MatchupList
import bgroseclose.leagueofyou.Retrofit.IRiotClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardFragPresenter(val view: View, val riotClient: IRiotClient) {

    fun getMatchups(accountId: String) {
        view.loadMatchup(true)
        val call: Call<MatchupList>? = riotClient.getMatchupsDashboard(accountId)
        call!!.enqueue(object : Callback<MatchupList> {
            override fun onFailure(call: Call<MatchupList>, t: Throwable) {
                view.loadMatchup(false)
                view.displayError()
            }

            override fun onResponse(call: Call<MatchupList>, response: Response<MatchupList>) {
                view.loadMatchup(false)
                if(response.body() != null) {
                    view.setMatchupAdapter(response.body()!!)
                } else {
                    view.displayError()
                }
            }

        })
    }

    interface View {
        fun loadMatchup(isVisible: Boolean)
        fun displayError()
        fun setMatchupAdapter(matchups: MatchupList)
    }
}
