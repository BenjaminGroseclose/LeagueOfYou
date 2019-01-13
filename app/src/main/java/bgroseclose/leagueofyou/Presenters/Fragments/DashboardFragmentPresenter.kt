package bgroseclose.leagueofyou.Presenters.Fragments

import android.content.Context
import bgroseclose.leagueofyou.Activites.GameAnalysisActivityIntent
import bgroseclose.leagueofyou.Models.MatchModels.Match
import bgroseclose.leagueofyou.Models.MatchupModels.Matchup
import bgroseclose.leagueofyou.Models.MatchupModels.MatchupList
import bgroseclose.leagueofyou.Retrofit.IRiotClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardFragmentPresenter(val view: View, val riotClient: IRiotClient) {

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
                    getMatchupDetails(response.body()!!)
                } else {
                    view.displayError()
                }
            }
        })
    }

    fun openAnaylis(context: Context) {
        context.startActivity(context.GameAnalysisActivityIntent())
    }

    private fun getMatchupDetails(matchups: MatchupList) {
        val matches: MutableList<Match> = mutableListOf(Match())
        for(matchup: Matchup in matchups.matches!!) {
            val call: Call<Match>? = riotClient.getMatchById(matchup.gameId.toString())
            call!!.enqueue(object : Callback<Match> {
                override fun onFailure(call: Call<Match>, t: Throwable) {
                    view.loadMatchup(false)
                    view.displayError()
                }

                override fun onResponse(call: Call<Match>, response: Response<Match>) {
                    view.loadMatchup(false)
                    if(response.body() != null) {
                        matches.add(response.body()!!)
                    } else {
                        view.displayError()
                    }
                }
            })
        }
        if(matches.size == 0) {
            view.setMatchupAdapter(matches)
        } else {
            view.displayError()
        }
    }

    interface View {
        fun loadMatchup(isVisible: Boolean)
        fun displayError()
        fun setMatchupAdapter(matches: List<Match>)
    }
}
