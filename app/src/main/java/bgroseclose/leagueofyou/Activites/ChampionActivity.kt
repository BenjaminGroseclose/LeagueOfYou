package bgroseclose.leagueofyou.Activites

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.View

import bgroseclose.leagueofyou.Components.DaggerChampionComponent
import bgroseclose.leagueofyou.Fragments.ChampionBuildFragment
import bgroseclose.leagueofyou.Fragments.ChampionOverviewFragment
import bgroseclose.leagueofyou.Fragments.ChampionSpellsFragment
import bgroseclose.leagueofyou.Models.ChampionModels.Champion
import bgroseclose.leagueofyou.Modules.ChampionModule
import bgroseclose.leagueofyou.Presenters.Activities.ChampionPresenter
import bgroseclose.leagueofyou.R

import butterknife.ButterKnife

import kotlinx.android.synthetic.main.activity_champion.*

import javax.inject.Inject

import bgroseclose.leagueofyou.LeagueOfYouSingleton.Constants as Constants

class ChampionActivity: FragmentActivity(), ChampionPresenter.ChampionView {

    companion object {
        lateinit var champion: Champion

    }

    lateinit var presenter: ChampionPresenter
    @Inject set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_champion)
        ButterKnife.bind(this)

        DaggerChampionComponent.builder()
                .championModule(ChampionModule(this))
                .build()
                .inject(this)

        val championName = intent.getStringExtra(Constants.CHAMPION_NAME_EXTRA)

        presenter.getChampion(championName)
        champion_view_pager.adapter = ScreenSlidePagerAdapter(supportFragmentManager)
        val tabLayout: TabLayout = findViewById(R.id.champion_sliding_tabs)
        tabLayout.setupWithViewPager(champion_view_pager)
    }

    private fun displayAlertDialog(title: String, message: String) {
        val dialog = android.app.AlertDialog.Builder(this)
        dialog.setTitle(title)
                .setMessage(message)
                .setIcon(R.drawable.ic_alert)
                .setNeutralButton(getString(R.string.ok), null)
        dialog.create()
        dialog.show()
    }

    override fun loadChampion(isVisible: Boolean) {
        if(isVisible) {
            champion_progress_bar.visibility = View.VISIBLE
        } else {
            champion_progress_bar.visibility = View.GONE
        }
    }

    override fun displayServerError() {
        displayAlertDialog(
                getString(R.string.server_error_title),
                getString(R.string.server_error_message)
        )
    }

    override fun onBackPressed() {
        if(champion_view_pager.currentItem == 0) {
            super.onBackPressed()
        } else {
            champion_view_pager.currentItem = champion_view_pager.currentItem - 1
        }
    }

    private inner class ScreenSlidePagerAdapter(fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager) {
        override fun getItem(position: Int): Fragment {
            return when(position) {
                0 -> ChampionOverviewFragment()
                1 -> ChampionBuildFragment()
                2 -> ChampionSpellsFragment()
                else -> {
                    ChampionOverviewFragment()
                }
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when(position) {
                0 -> getString(R.string.overview)
                1 -> getString(R.string.builds)
                2 -> getString(R.string.spells)
                else -> {
                    getString(R.string.overview)
                }
            }
        }

        override fun getCount(): Int = Constants.CHAMPION_PAGE_COUNT
    }
}