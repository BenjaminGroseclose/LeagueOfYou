package bgroseclose.leagueofyou.Activites;

import android.app.AlertDialog;
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
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import javax.inject.Inject;

import bgroseclose.leagueofyou.Components.DaggerIRiotClientComponent;
import bgroseclose.leagueofyou.Components.IRiotClientComponent;
import bgroseclose.leagueofyou.Fragments.DashboardFragment;
import bgroseclose.leagueofyou.LeagueOfYouSingleton;
import bgroseclose.leagueofyou.Models.LeagueOfYouAccount;
import bgroseclose.leagueofyou.Presenters.Activities.DashboardPresenter;
import bgroseclose.leagueofyou.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class DashboardActivity extends AppCompatActivity implements DashboardPresenter.View {

    @Inject private IRiotClientComponent riotClientComponent;
    private DashboardPresenter presenter;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Fragment existingFragment;
    private LeagueOfYouAccount leagueOfYouAccount;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        riotClientComponent = DaggerIRiotClientComponent.builder()
                .build();

        leagueOfYouAccount = LeagueOfYouSingleton.getLeagueOfYouAccount();
        progressBar = findViewById(R.id.dashboard_progress_bar);

        presenter = new DashboardPresenter(this, leagueOfYouAccount, riotClientComponent.getRiotClient());
        presenter.loadSummoner();

        initDrawerAndToolbar();
    }

    private void initDrawerAndToolbar() {
        toolbar = findViewById(R.id.dashboard_toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_drawer);

        drawer = findViewById(R.id.dashboard_drawer_layout);
        navigationView = findViewById(R.id.dashboard_drawer);
        View header = navigationView.getHeaderView(0);
        CircleImageView profileImageView = header.findViewById(R.id.nav_drawer_profile_pic);
        TextView txtSummonerName = header.findViewById(R.id.nav_drawer_summoner_name);
        TextView txtSummonerLevel = header.findViewById(R.id.nav_drawer_summoner_lvl);
        ImageView imageRankIcon = header.findViewById(R.id.nav_drawer_rank_icon);
        TextView txtRankName = header.findViewById(R.id.nav_drawer_rank_name);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close
        );
        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        profileImageView.setImageURI(Uri.parse(LeagueOfYouSingleton.getSummonerProfileIcon()));
        txtSummonerName.setText(leagueOfYouAccount.getSummonerName());
        txtSummonerLevel.setText("Lvl ".concat(String.valueOf(leagueOfYouAccount.getSummonerInfo().getSummonerLevel())));
//        imageRankIcon.setImageDrawable(getDrawable(leagueOfYouAccount.getSummonerInfo().getSummonerRankedInfo().getRankedIcon()));
//        txtRankName.setText(leagueOfYouAccount.getSummonerInfo().getSummonerRankedInfo());

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


    private void displayAlertDialog(String title, String message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(title)
                .setMessage(message)
                .setIcon(R.drawable.ic_alert)
                .setNeutralButton(getString(R.string.ok), null);
        dialog.create();
        dialog.show();
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

    @Override
    public void loadDashboard(boolean isVisible) {
        if(isVisible) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void displayServerError() {
        displayAlertDialog(
                getString(R.string.server_error_title),
                getString(R.string.server_error_message)
        );
    }

}
