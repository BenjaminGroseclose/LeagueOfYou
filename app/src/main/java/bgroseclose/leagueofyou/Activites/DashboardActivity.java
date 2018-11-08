package bgroseclose.leagueofyou.Activites;

import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import bgroseclose.leagueofyou.Fragments.DashboardFragment;
import bgroseclose.leagueofyou.LeagueOfYouSingleton;
import bgroseclose.leagueofyou.Models.LeagueOfYouAccount;
import bgroseclose.leagueofyou.Presenters.Activities.DashboardPresenter;
import bgroseclose.leagueofyou.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class DashboardActivity extends AppCompatActivity implements DashboardPresenter.View {

    private DashboardPresenter presenter;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Fragment existingFragment;
    private LeagueOfYouAccount leagueOfYouAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        leagueOfYouAccount = LeagueOfYouSingleton.getLeagueOfYouAccount();

        presenter = new DashboardPresenter(this);
        initDrawerAndToolbar();
        presenter.loadSummoner();
    }

    private void initDrawerAndToolbar() {
        toolbar = findViewById(R.id.dashboard_toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_drawer);

        drawer = findViewById(R.id.dashboard_drawer_layout);
        CircleImageView profileImageView = findViewById(R.id.nav_drawer_profile_pic);
        TextView txtSummonerName = findViewById(R.id.nav_drawer_summoner_name);
        TextView txtSummonerLevel = findViewById(R.id.nav_drawer_summoner_lvl);
        ImageView imageRankIcon = findViewById(R.id.nav_drawer_rank_icon);
        TextView txtRankName = findViewById(R.id.nav_drawer_rank_name);
        navigationView = findViewById(R.id.dashboard_drawer);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close
        );
        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        profileImageView.setImageURI(Uri.parse(LeagueOfYouSingleton.getSummonerProfileIcon()));
        txtSummonerName.setText(leagueOfYouAccount.getSummonerName());
        txtSummonerLevel.setText("Lvl ".concat(String.valueOf(leagueOfYouAccount.getSummonerInfo().getSummonerLevel())));
        imageRankIcon.setImageDrawable(getDrawable(leagueOfYouAccount.getSummonerInfo().getSummonerRankedInfo().getRankedIcon()));
        txtRankName.setText(leagueOfYouAccount.getSummonerInfo().getSummonerRankedInfo().getRankedName());

        setupDrawerContent(navigationView);
        initDashboardFragment();
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        presenter.drawerItemSelected(menuItem.getItemId());
                        return true;
                    }
                });
    }

    private void initDashboardFragment() {
        if (findViewById(R.id.dashboard_container) != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.dashboard_container, new DashboardFragment());
            fragmentTransaction.commit();
            existingFragment = new DashboardFragment();
        }
        toolbar.setTitle(getString(R.string.dashbaord));
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            onBackPressed();
        }
    }

}
