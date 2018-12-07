package bgroseclose.leagueofyou.Activites

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.*
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.Toolbar

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

fun Context.championActivityIntent(championName: String) : Intent {
    return Intent(this, ChampionActivity::class.java)
            .putExtra(Constants.CHAMPION_NAME_EXTRA, championName)
}

class ChampionActivity: AppCompatActivity(), ChampionPresenter.ChampionView {

    companion object {
        lateinit var champion: Champion

        fun openChampionActivity(context: Context, championName: String) : Intent{
            return context.championActivityIntent(championName)
        }
    }

    lateinit var presenter: ChampionPresenter
    @Inject set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_champion)
        ButterKnife.bind(this)

        val championName = intent.getStringExtra(Constants.CHAMPION_NAME_EXTRA)

        setSupportActionBar(champion_toolbar as android.support.v7.widget.Toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.title = championName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        DaggerChampionComponent.builder()
                .championModule(ChampionModule(this))
                .build()
                .inject(this)

        if(intent == null) {
            throw RuntimeException("Champion Activity needs a champion name passed in the arguments")
        }

        presenter.getChampion(championName)
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun displayServerError() {
        displayAlertDialog(
                getString(R.string.server_error_title),
                getString(R.string.server_error_message)
        )
    }

    override fun setViewPager() {
        champion_view_pager.adapter = ScreenSlidePagerAdapter(supportFragmentManager)
        val tabLayout = findViewById<TabLayout>(R.id.champion_sliding_tabs)
        tabLayout.post { tabLayout.setupWithViewPager(champion_view_pager) }
    }

    override fun onBackPressed() {
        if(champion_view_pager.currentItem == 0) {
            super.onBackPressed()
        } else {
            champion_view_pager.currentItem = champion_view_pager.currentItem - 1
        }
    }

    private inner class ScreenSlidePagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {
        override fun getItem(position: Int): Fragment {
            lateinit var fragment: Fragment
            when(position) {
                0 -> fragment = ChampionOverviewFragment()
                1 -> fragment = ChampionBuildFragment()
                2 -> fragment = ChampionSpellsFragment()
                else -> {
                    fragment = ChampionOverviewFragment()
                }
            }
            return fragment
        }

        override fun getPageTitle(position: Int): CharSequence? {
            val title: String
            when(position) {
                0 -> title = getString(R.string.overview)
                1 -> title = getString(R.string.builds)
                2 -> title = getString(R.string.spells)
                else -> {
                    title = getString(R.string.overview)
                }
            }
            return title
        }

        override fun getCount(): Int = Constants.CHAMPION_PAGE_COUNT
    }
}