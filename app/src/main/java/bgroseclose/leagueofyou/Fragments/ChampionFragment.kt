package bgroseclose.leagueofyou.Fragments

import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bgroseclose.leagueofyou.Adapters.ChampionSkinAdapter
import bgroseclose.leagueofyou.Components.DaggerIStaticLeagueComponent
import bgroseclose.leagueofyou.Components.IStaticLeagueComponent
import bgroseclose.leagueofyou.Models.ChampionModels.Champion
import bgroseclose.leagueofyou.Models.ChampionModels.ChampionSkins
import bgroseclose.leagueofyou.Modules.ContextModule
import bgroseclose.leagueofyou.Presenters.Fragments.ChampionPresenter
import bgroseclose.leagueofyou.R
import bgroseclose.leagueofyou.R.id.champion_skin_image_pager
import kotlinx.android.synthetic.main.fragment_champion.*

class ChampionFragment: Fragment(), ChampionPresenter.ChampionView {

    lateinit var component: IStaticLeagueComponent
    lateinit var presenter: ChampionPresenter
    lateinit var champion: Champion

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_champion, container, false)

        var bundle = this.arguments

        component = DaggerIStaticLeagueComponent.builder()
                .contextModule(ContextModule(context))
                .build()

        presenter = ChampionPresenter(component.staticLeagueClient, this)
        bundle?.let {
            presenter.getChampionData(it.getString(getString(R.string.champion_name_bundle), null))
        }
        return rootView
    }

    private fun displayAlertDialog(title: String, message: String) {
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle(title)
                .setMessage(message)
                .setIcon(R.drawable.ic_alert)
                .setCancelable(false)
                .setNeutralButton(getString(R.string.ok), null)
        dialog.create()
        dialog.show()
    }

    private fun getChampionSkins(champion: Champion): ArrayList<String> {
        lateinit var championSkins: ArrayList<String>

        for(skins: ChampionSkins in champion.skins!!) {
            championSkins.add(skins.id)
        }

        return championSkins
    }

    override fun setPagerAdater(champion: Champion) {
        activity?.let  {
            this.champion = champion
            champion_skin_image_pager.adapter = ChampionSkinAdapter(it, getChampionSkins(champion), component.picasso)
        }
        updateUI(champion)
    }

    override fun progressBar(isVisible: Boolean) {
        if(isVisible) {
            champion_progress_bar.visibility = View.VISIBLE
        } else {
            champion_progress_bar.visibility = View.GONE
        }
    }

    override fun displayServerError() {
        displayAlertDialog(
                getString(R.string.server_error_title),
                getString(R.string.server_error_message))
    }

    override fun updateUI(champion: Champion) {

    }


}