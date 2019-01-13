package bgroseclose.leagueofyou.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import bgroseclose.leagueofyou.Models.ChampionModels.Champion
import bgroseclose.leagueofyou.R

class ChampionBuildFragment : Fragment() {

    companion object {
        fun newInstance(champion: Champion) : Fragment {
            val fragment = ChampionBuildFragment()

            val args = Bundle()
            args.putSerializable(CHAMPION_EXTRA, champion)

            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_champion_builds, container, false)

        return rootView
    }

}
