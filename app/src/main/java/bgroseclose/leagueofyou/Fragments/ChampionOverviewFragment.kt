package bgroseclose.leagueofyou.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bgroseclose.leagueofyou.R

/**
 * Will display basic champion data. Also will show good and bad matchups
 * The matchup data will be gathered over time and saved in Firebase Database (async update)
 */

class ChampionOverviewFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_champion_overview, container, false)

        return rootView
    }
}
