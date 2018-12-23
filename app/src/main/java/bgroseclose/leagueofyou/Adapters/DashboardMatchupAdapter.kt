package bgroseclose.leagueofyou.Adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import bgroseclose.leagueofyou.Models.MatchupModels.MatchupList
import com.squareup.picasso.Picasso

class DashboardMatchupAdapter(val matchups: MatchupList, val picasso: Picasso): RecyclerView.Adapter<DashboardMatchupAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int = matchups.totalGames!!

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {

        }
    }
}