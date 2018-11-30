package bgroseclose.leagueofyou.Activites

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import bgroseclose.leagueofyou.Presenters.Activities.ChampionPresenter
import bgroseclose.leagueofyou.R
import butterknife.ButterKnife

import bgroseclose.leagueofyou.LeagueOfYouSingleton.Constants as Constants



class ChampionActivity: AppCompatActivity(), ChampionPresenter.ChampionView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_champion)
        ButterKnife.bind(this)

        val championName = intent.getStringExtra(Constants.CHAMPION_NAME_EXTRA)

    }
}