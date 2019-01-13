package bgroseclose.leagueofyou.Activites

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import bgroseclose.leagueofyou.ViewModels.GameAdapterViewModel

fun Context.GameAnalysisActivityIntent() : Intent {
    return Intent(this, GameAnalysisActivity::class.java)
}

class GameAnalysisActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val model = ViewModelProviders.of(this).get(GameAdapterViewModel::class.java)

    }

}