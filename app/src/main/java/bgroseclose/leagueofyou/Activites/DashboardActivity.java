package bgroseclose.leagueofyou.Activites;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
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

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import bgroseclose.leagueofyou.Components.DaggerIApplicationComponent;
import bgroseclose.leagueofyou.Components.IApplicationComponent;
import bgroseclose.leagueofyou.Fragments.BuildFragment;
import bgroseclose.leagueofyou.Fragments.ChampionListFragment;
import bgroseclose.leagueofyou.Fragments.DashboardFragment;
import bgroseclose.leagueofyou.LeagueOfYouSingleton;
import bgroseclose.leagueofyou.Models.LeagueOfYouAccount;
import bgroseclose.leagueofyou.Modules.ContextModule;
import bgroseclose.leagueofyou.Presenters.Activities.DashboardPresenter;
import bgroseclose.leagueofyou.R;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class DashboardActivity extends AppCompatActivity implements DashboardPresenter.View {

    @Inject
    IApplicationComponent applicationComponent;
    private DashboardPresenter presenter;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Fragment existingFragment;
    private LeagueOfYouAccount leagueOfYouAccount;
    private ProgressBar progressBar;
    private Picasso picasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        applicationComponent = DaggerIApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
        picasso = applicationComponent.getPicasso();

        leagueOfYouAccount = LeagueOfYouSingleton.getLeagueOfYouAccount();
        progressBar = findViewById(R.id.dashboard_progress_bar);

        presenter = new DashboardPresenter(this, applicationComponent.getRiotClient());
        presenter.loadSummoner();
    }

    @Override
    public void initDrawerAndToolbar() {
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

        picasso.load(LeagueOfYouSingleton.getSummonerProfileIcon()).into(profileImageView);
        txtSummonerName.setText(leagueOfYouAccount.getSummonerName());
        txtSummonerLevel.setText("Lvl ".concat(String.valueOf(leagueOfYouAccount.getSummonerInfo().getSummonerLevel())));
        setRank(imageRankIcon, txtRankName);

        setupDrawerContent(navigationView);
        initDashboardFragment();
    }

    private void setRank(ImageView rankedIcon, TextView rankedText) {
        if(LeagueOfYouSingleton.getSoloQueue() > -1 ) {
            String rank = leagueOfYouAccount.getSummonerInfo().getSummonerRankedInfo().get(LeagueOfYouSingleton.getSoloQueue()).getRank();
            String tier = leagueOfYouAccount.getSummonerInfo().getSummonerRankedInfo().get(LeagueOfYouSingleton.getSoloQueue()).getTier();
            rankedText.setText(tier.concat(" ").concat(rank));
            rankedIcon.setImageDrawable(setRankedIcon(tier));
        } else {
            rankedIcon.setVisibility(View.GONE);
            rankedText.setVisibility(View.GONE);
        }

    }

    private Drawable setRankedIcon(String tier) {
        Drawable retval;
        int rankLocation = -1;
        String[] rankedNames = getResources().getStringArray(R.array.ranked_names);
        for(int i = 0; i < rankedNames.length; i++) {
            if(rankedNames[i].equals(tier)) {
                rankLocation = i;
            }
        }
        switch (rankLocation) {
            case 0:
                retval = getDrawable(R.drawable.bronze);
                break;
            case 1:
                retval = getDrawable(R.drawable.silver);
                break;
            case 2:
                retval = getDrawable(R.drawable.gold);
                break;
            case 3:
                retval = getDrawable(R.drawable.platinum);
                break;
            case 4:
                retval = getDrawable(R.drawable.diamond);
                break;
            case 5:
                retval = getDrawable(R.drawable.master);
                break;
            case 6:
                retval = getDrawable(R.drawable.challenger);
                break;
            default:
                retval = getDrawable(R.drawable.provisional);
                break;
        }
        return retval;
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

    @Override
    public void openDashboardFragment() {
        if(existingFragment !=  new DashboardFragment()) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.dashboard_container, new DashboardFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        drawer.closeDrawers();
    }

    @Override
    public void openChampionFragment() {
        if(existingFragment !=  new ChampionListFragment()) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.dashboard_container, new ChampionListFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        drawer.closeDrawers();
    }

    @Override
    public void openBuildFragment() {
        if(existingFragment !=  new BuildFragment()) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.dashboard_container, new BuildFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        drawer.closeDrawers();
    }

    @Override
    public void logout() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        FirebaseAuth.getInstance().signOut();
        finish();
    }
}
