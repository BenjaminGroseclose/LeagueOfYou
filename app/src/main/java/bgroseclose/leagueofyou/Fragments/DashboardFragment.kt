package bgroseclose.leagueofyou.Fragments

import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bgroseclose.leagueofyou.Adapters.DashboardMatchHistoryAdapter
import bgroseclose.leagueofyou.Components.DaggerDashboardFragmentComponent
import bgroseclose.leagueofyou.Models.MatchModels.Match
import bgroseclose.leagueofyou.Presenters.Fragments.DashboardFragmentPresenter

import bgroseclose.leagueofyou.R
import butterknife.ButterKnife
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.lang.UnsupportedOperationException
import javax.inject.Inject

const val ACCOUNT_ID_EXTRA = "account_id_extra"

class DashboardFragment : Fragment(), DashboardFragmentPresenter.View {

    companion object {
        fun newInstance(accountId: String): Fragment {
            val fragment = DashboardFragment()

            val args = Bundle()
            args.putString(ACCOUNT_ID_EXTRA, accountId)

            fragment.arguments = args
            return fragment
        }
    }

    lateinit var presenter: DashboardFragmentPresenter
    @Inject set
    lateinit var picasso: Picasso
    @Inject set

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_dashboard, container, false)
        ButterKnife.bind(this, rootView)

        DaggerDashboardFragmentComponent.builder().build().inject(this)
        DaggerDashboardFragmentComponent.builder().build().inject(picasso)


        if(arguments == null) {
            throw UnsupportedOperationException("Must pass an accountId into Dashboard Fragment")
        }

        val accountId = arguments!!.getString(ACCOUNT_ID_EXTRA)

        presenter.getMatchups(accountId = accountId)

        return rootView
    }

    private fun displayAlertDialog(title: String, message: String) {
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle(title)
                .setMessage(message)
                .setIcon(R.drawable.ic_alert)
                .setNeutralButton(getString(R.string.ok), null)
        dialog.create()
        dialog.show()
    }


    override fun displayError() {
        displayAlertDialog(
                getString(R.string.server_error_title),
                getString(R.string.server_error_message)
        )
    }

    override fun setMatchupAdapter(matches: List<Match>) {
        dashboard_match_up_recycler_view.adapter = DashboardMatchHistoryAdapter(matches, picasso)
    }

    override fun loadMatchup(isVisible: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
