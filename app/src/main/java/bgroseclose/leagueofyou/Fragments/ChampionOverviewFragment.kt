package bgroseclose.leagueofyou.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bgroseclose.leagueofyou.Activites.ChampionActivity
import bgroseclose.leagueofyou.Models.ChampionModels.Champion
import bgroseclose.leagueofyou.R
import kotlinx.android.synthetic.main.fragment_champion_overview.view.*

/**
 * Will display basic champion data. Also will show good and bad matchups
 * The matchup data will be gathered over time and saved in Firebase Database (async update)
 */

class ChampionOverviewFragment : Fragment() {

    val currentChampion = ChampionActivity.champion.wrapper?.champion ?:
        throw RuntimeException("Was not able to retrieve champion data correctly. ")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_champion_overview, container, false);

        rootView.champion_title.text = currentChampion.name

        for(tag in currentChampion.tags!!) {
            val string = rootView.champion_tags.text.toString() + tag + " "
            rootView.champion_tags.text = string
        }

        return rootView
    }

}
