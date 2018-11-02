package bgroseclose.leagueofyou.Activites;

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

import bgroseclose.leagueofyou.Fragments.DashboardFragment;
import bgroseclose.leagueofyou.Presenters.Activities.DashboardPresenter;
import bgroseclose.leagueofyou.R;

public class DashboardActivity extends AppCompatActivity implements DashboardPresenter.View {

    private DashboardPresenter presenter;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Fragment existingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        presenter = new DashboardPresenter(this);
        initDrawerAndToolbar();
        presenter.loadSummoner();
    }

    private void initDrawerAndToolbar() {
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_drawer);


        drawer = findViewById(R.id.dashboard_drawer_layout);
        navigationView = findViewById(R.id.dashboard_drawer);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close
        );
        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

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
        toolbar.setTitle(getString(R.string.login));
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
