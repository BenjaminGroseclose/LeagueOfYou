package bgroseclose.leagueofyou.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bgroseclose.leagueofyou.Models.MatchModels.Match
import bgroseclose.leagueofyou.Models.MatchModels.Participant
import bgroseclose.leagueofyou.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.match_history_list_item.view.*

class DashboardMatchHistoryAdapter(val matches: List<Match>, val picasso: Picasso):
        RecyclerView.Adapter<DashboardMatchHistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        val inflate = LayoutInflater.from(viewGroup.context)
        return ViewHolder(inflate.inflate(R.layout.match_history_list_item, viewGroup, false))
    }

    override fun getItemCount(): Int = matches.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val currentMatch = matches[position]
        val participants = getAccountParticipant(currentMatch)
        viewHolder.championNameText.text = participants.getChampionName()
    }

    private fun getAccountParticipant(match: Match) : Participant {
        var returnParticipant: Participant? = null
        for (participant: Participant in match.participants!!) {
            returnParticipant = participant
        }
        return returnParticipant!!
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val championImage = itemView.matchup_champion_img
        val championNameText = itemView.matchup_champion_name
        val championLayout = itemView.matchup_layout
        val scoreText = itemView.matchup_score
        val gameTypeText = itemView.matchup_game_type
        val laneImage = itemView.matchup_lane
    }
}