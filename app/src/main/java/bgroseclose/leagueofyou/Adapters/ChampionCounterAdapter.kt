package bgroseclose.leagueofyou.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import bgroseclose.leagueofyou.Helpers.convertToPercent
import bgroseclose.leagueofyou.LeagueOfYouSingleton
import bgroseclose.leagueofyou.Models.ChampionModels.MatchupChampion
import bgroseclose.leagueofyou.R
import com.squareup.picasso.Picasso

class ChampionCounterAdapter(val matchupChampions: List<MatchupChampion>, val picasso: Picasso) : RecyclerView.Adapter<ChampionCounterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        return ViewHolder(inflater.inflate(R.layout.matchup_champion_list_item, viewGroup, false))
    }

    override fun getItemCount(): Int = matchupChampions.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val matchupChampion = matchupChampions[position]
        holder.matchupName.text = matchupChampion.championName
        holder.matchupWinRate.text = matchupChampion.championWinRate?.convertToPercent()
        picasso.load(LeagueOfYouSingleton.getChampionIcon(matchupChampion.championId)).into(holder.matchupImage)


    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val matchupLayout: LinearLayout = itemView.findViewById(R.id.match_up_layout)
        val matchupImage: ImageView = itemView.findViewById(R.id.match_up_image)
        val matchupName: TextView = itemView.findViewById(R.id.match_up_text)
        val matchupWinRate: TextView = itemView.findViewById(R.id.match_up_win_rate)

    }
}