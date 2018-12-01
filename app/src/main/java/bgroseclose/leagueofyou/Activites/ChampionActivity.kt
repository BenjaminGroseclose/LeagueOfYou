package bgroseclose.leagueofyou.Activites

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import bgroseclose.leagueofyou.Components.DaggerChampionComponent
import bgroseclose.leagueofyou.Models.ChampionModels.Champion
import bgroseclose.leagueofyou.Modules.ChampionModule
import bgroseclose.leagueofyou.Presenters.Activities.ChampionPresenter
import bgroseclose.leagueofyou.R
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.activity_champion.*
import javax.inject.Inject

import bgroseclose.leagueofyou.LeagueOfYouSingleton.Constants as Constants



class ChampionActivity: AppCompatActivity(), ChampionPresenter.ChampionView {

    companion object {
        var champion: Champion? = null
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

}