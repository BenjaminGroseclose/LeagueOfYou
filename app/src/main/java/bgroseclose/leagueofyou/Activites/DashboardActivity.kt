package bgroseclose.leagueofyou.Activites

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment

import com.google.firebase.auth.FirebaseAuth

import com.squareup.picasso.Picasso

import bgroseclose.leagueofyou.Components.DaggerApplicationComponent
import bgroseclose.leagueofyou.Components.ApplicationComponent
import bgroseclose.leagueofyou.Fragments.ChampionListFragment
import bgroseclose.leagueofyou.Fragments.DashboardFragment
import bgroseclose.leagueofyou.LeagueOfYouSingleton
import bgroseclose.leagueofyou.Models.LeagueOfYouAccount
import bgroseclose.leagueofyou.Modules.ContextModule
import bgroseclose.leagueofyou.Presenters.Activities.DashboardPresenter
import bgroseclose.leagueofyou.R
import butterknife.ButterKnife
import com.google.android.material.navigation.NavigationView
import de.hdodenhof.circleimageview.CircleImageView

fun Context.DashboardActivityIntent() : Intent {
    return Intent(this, DashboardActivity::class.java)
}

class DashboardActivity : AppCompatActivity(), DashboardPresenter.View {

    private var applicationComponent: ApplicationComponent? = null
    private var picasso: Picasso? = null
    private var presenter: DashboardPresenter? = null
    private var toolbar: Toolbar? = null
    private var drawer: DrawerLayout? = null
    private var navigationView: NavigationView? = null
    private var existingFragment: Fragment? = null
    private var leagueOfYouAccount: LeagueOfYouAccount? = null
    private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        ButterKnife.bind(this)

        applicationComponent = DaggerApplicationComponent.builder()
                .contextModule(ContextModule(this))
                .build()

        picasso = applicationComponent!!.picasso

        leagueOfYouAccount = LeagueOfYouSingleton.getLeagueOfYouAccount()
        progressBar = findViewById(R.id.dashboard_progress_bar)

        presenter = DashboardPresenter(this, applicationComponent!!.riotClient)
        presenter!!.loadSummoner()
    }

    override fun initDrawerAndToolbar() {
        toolbar = findViewById(R.id.dashboard_toolbar)
        setSupportActionBar(toolbar)

        toolbar!!.setNavigationIcon(R.drawable.ic_drawer)

        drawer = findViewById(R.id.dashboard_drawer_layout)
        navigationView = findViewById(R.id.dashboard_drawer)
        val header = navigationView!!.getHeaderView(0)
        val profileImageView = header.findViewById<CircleImageView>(R.id.nav_drawer_profile_pic)
        val txtSummonerName = header.findViewById<TextView>(R.id.nav_drawer_summoner_name)
        val txtSummonerLevel = header.findViewById<TextView>(R.id.nav_drawer_summoner_lvl)
        val imageRankIcon = header.findViewById<ImageView>(R.id.nav_drawer_rank_icon)
        val txtRankName = header.findViewById<TextView>(R.id.nav_drawer_rank_name)
        val drawerToggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close
        )
        drawer!!.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        picasso!!.load(LeagueOfYouSingleton.getSummonerProfileIcon()).into(profileImageView)
        txtSummonerName.text = leagueOfYouAccount!!.summonerName
        txtSummonerLevel.text = "Lvl " + leagueOfYouAccount!!.summonerInfo.summonerLevel.toString()
        setRank(imageRankIcon, txtRankName)

        setupDrawerContent(navigationView!!)
        initDashboardFragment()
    }

    private fun setRank(rankedIcon: ImageView, rankedText: TextView) {
        if (LeagueOfYouSingleton.getSoloQueue() > -1) {
            val rank = leagueOfYouAccount!!.summonerInfo.summonerRankedInfo[LeagueOfYouSingleton.getSoloQueue()].rank
            val tier = leagueOfYouAccount!!.summonerInfo.summonerRankedInfo[LeagueOfYouSingleton.getSoloQueue()].tier
            rankedText.text = "$tier $rank"
            rankedIcon.setImageDrawable(setRankedIcon(tier))
        } else {
            rankedIcon.visibility = View.GONE
            rankedText.visibility = View.GONE
        }

    }

    private fun setRankedIcon(tier: String): Drawable? {
        val retval: Drawable?
        var rankLocation = -1
        val rankedNames = resources.getStringArray(R.array.ranked_names)
        for (i in rankedNames.indices) {
            if (rankedNames[i] == tier) {
                rankLocation = i
            }
        }
        when (rankLocation) {
            0 -> retval = getDrawable(R.drawable.iron_emblem)
            1 -> retval = getDrawable(R.drawable.bronze_emblem)
            2 -> retval = getDrawable(R.drawable.silver_emblem)
            3 -> retval = getDrawable(R.drawable.gold_emblem)
            4 -> retval = getDrawable(R.drawable.diamond_emblem)
            5 -> retval = getDrawable(R.drawable.master_emblem)
            6 -> retval = getDrawable(R.drawable.grandmaster_emblem)
            7 -> retval = getDrawable(R.drawable.challenger_emblem)
            else -> retval = getDrawable(R.drawable.provisional)
        }
        return retval
    }

    private fun setupDrawerContent(navigationView: NavigationView) {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            presenter!!.drawerItemSelected(menuItem.itemId)
            true
        }
    }

    private fun initDashboardFragment() {
        if (findViewById<View>(R.id.dashboard_container) != null) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.dashboard_container, DashboardFragment.newInstance(leagueOfYouAccount!!.summonerInfo.accountId))
            fragmentTransaction.commit()
            existingFragment = DashboardFragment()
        }
        toolbar!!.title = getString(R.string.dashbaord)
    }


    private fun displayAlertDialog(title: String, message: String) {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle(title)
                .setMessage(message)
                .setIcon(R.drawable.ic_alert)
                .setNeutralButton(getString(R.string.ok), null)
        dialog.create()
        dialog.show()
    }

    override fun onBackPressed() {
        if (drawer!!.isDrawerOpen(GravityCompat.START)) {
            drawer!!.closeDrawer(GravityCompat.START)
        } else if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            onBackPressed()
        }
    }

    override fun loadDashboard(isVisible: Boolean) {
        if (isVisible) {
            progressBar!!.visibility = View.VISIBLE
        } else {
            progressBar!!.visibility = View.GONE
        }
    }

    override fun displayServerError() {
        displayAlertDialog(
                getString(R.string.server_error_title),
                getString(R.string.server_error_message)
        )
    }

    override fun openDashboardFragment() {
        if (existingFragment !== DashboardFragment()) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.dashboard_container, DashboardFragment())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        drawer!!.closeDrawers()
    }

    override fun openChampionFragment() {
        if (existingFragment !== ChampionListFragment()) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.dashboard_container, ChampionListFragment())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        drawer!!.closeDrawers()
    }

    override fun openBuildFragment() {}

    override fun logout() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        FirebaseAuth.getInstance().signOut()
        finish()
    }
}
