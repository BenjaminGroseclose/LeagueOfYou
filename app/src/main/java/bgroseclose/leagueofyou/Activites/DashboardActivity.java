package bgroseclose.leagueofyou.Activites;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import bgroseclose.leagueofyou.Presenters.DashboardActivityPresenter;
import bgroseclose.leagueofyou.R;

public class DashboardActivity extends AppCompatActivity implements DashboardActivityPresenter.View {

    private DashboardActivityPresenter presenter;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        presenter = new DashboardActivityPresenter(this);
        initDrawerAndToolbar();
        presenter.loadSummoner();
    }

    private void initDrawerAndToolbar() {
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationIcon(R.drawable.ic_drawer);

        drawer = findViewById(R.id.dashboard_drawer_layout);
        navigationView = findViewById(R.id.dashboard_drawer);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close
        );
        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        setupDrawerContent(navigationView);
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
}
