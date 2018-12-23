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
import bgroseclose.leagueofyou.Models.CounterChampion
import bgroseclose.leagueofyou.R
import com.squareup.picasso.Picasso

class ChampionCounterAdapter(val counterChampions: List<CounterChampion>, val picasso: Picasso) : RecyclerView.Adapter<ChampionCounterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        return ViewHolder(inflater.inflate(R.layout.matchup_champion_list_item, viewGroup, false))
    }

    override fun getItemCount(): Int = counterChampions.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val counterChampion = counterChampions[position]
        holder.matchupName.text = counterChampion.name
        holder.matchupWinRate.text = counterChampion.winRate?.convertToPercent()
        picasso.load(LeagueOfYouSingleton.getChampionIcon(counterChampion.id)).into(holder.matchupImage)


    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val matchupLayout: LinearLayout = itemView.findViewById(R.id.match_up_layout)
        val matchupImage: ImageView = itemView.findViewById(R.id.match_up_image)
        val matchupName: TextView = itemView.findViewById(R.id.match_up_text)
        val matchupWinRate: TextView = itemView.findViewById(R.id.match_up_win_rate)

    }
}