package bgroseclose.leagueofyou.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bgroseclose.leagueofyou.Models.ChampionModels.Champion
import bgroseclose.leagueofyou.R

class ChampionSpellsFragment : Fragment() {

    companion object {
        fun newInstance(champion: Champion) : Fragment {
            val fragment = ChampionSpellsFragment()

            val args = Bundle()
            args.putSerializable(CHAMPION_EXTRA, champion)

            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_champion_spells, container, false)

        return rootView
    }

}
