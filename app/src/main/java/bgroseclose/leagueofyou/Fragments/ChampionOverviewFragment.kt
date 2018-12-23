package bgroseclose.leagueofyou.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bgroseclose.leagueofyou.Components.DaggerChampionOverviewComponent
import bgroseclose.leagueofyou.Models.ChampionModels.Champion
import bgroseclose.leagueofyou.Models.ChampionModels.ChampionInfo
import bgroseclose.leagueofyou.Models.CounterChampion
import bgroseclose.leagueofyou.Models.CounterData
import bgroseclose.leagueofyou.Presenters.Fragments.ChampionOverviewPresenter
import bgroseclose.leagueofyou.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_champion_overview.*
import kotlinx.android.synthetic.main.fragment_champion_overview.view.*
import java.lang.UnsupportedOperationException
import javax.inject.Inject

/**
 * Will display basic champion data. Also will show good and bad matchups
 * The matchup data will be gathered over time and saved in Firebase Database (async update)
 */

const val CHAMPION_EXTRA = "champion_extra"

class ChampionOverviewFragment : Fragment(), ChampionOverviewPresenter.View {

    companion object {
        fun newInstance(champion: Champion) : Fragment {
            val fragment = ChampionOverviewFragment()

            val args = Bundle()
            args.putSerializable(CHAMPION_EXTRA, champion)

            fragment.arguments = args
            return fragment
        }
    }

    lateinit var picasso: Picasso
    @Inject set
    lateinit var presenter: ChampionOverviewPresenter
    @Inject set

    private lateinit var currentChampion: Champion
    lateinit var champion: ChampionInfo

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_champion_overview, container, false);

        if(arguments == null) {
            throw UnsupportedOperationException("Must pass a champion in the arguments.")
        }

        DaggerChampionOverviewComponent.builder().build().inject(this)
        DaggerChampionOverviewComponent.builder().build().inject(picasso)

        currentChampion = arguments!!.getSerializable(CHAMPION_EXTRA) as Champion
        champion = currentChampion.wrapper?.champion!!

        rootView.champion_title.text = champion.name

        for(tag in champion.tags!!) {
            val string = rootView.champion_tags.text.toString() + tag + " "
            rootView.champion_tags.text = string
        }

        progressBar(true)
        presenter.getMatchupData()

        return rootView
    }

    override fun progressBar(isVisible: Boolean) {
        champion_overview_progress_bar.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    override fun setCounterAdapter(counterChampions: CounterData) {
        TODO("not implemented -> completed after API is done.")
    }

}
