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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import bgroseclose.leagueofyou.Fragments.LoginFragment;
import bgroseclose.leagueofyou.Fragments.NewAccountFragment;
import bgroseclose.leagueofyou.Fragments.SupportFragment;
import bgroseclose.leagueofyou.Presenters.LoginPresenter;
import bgroseclose.leagueofyou.R;

public class LoginActivity extends AppCompatActivity implements LoginPresenter.View {

    private LoginPresenter presenter;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Fragment existingFragment;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenter(this);
        initDrawerAndToolbar();
        initLoginFragment();

    }

    private void initLoginFragment() {
        if (findViewById(R.id.login_fragment_container) != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.login_fragment_container, new LoginFragment());
            fragmentTransaction.commit();
            existingFragment = new LoginFragment();
        }
        toolbar.setTitle(getString(R.string.login));

    }

    private void initDrawerAndToolbar() {
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationIcon(R.drawable.ic_drawer);

        drawer = findViewById(R.id.login_drawer_layout);
        navigationView = findViewById(R.id.login_drawer);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_toolbar_menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.info) {
            //todo should we have this info icon.. what will it do?
        }
        return true;
    }

    @Override
    public void openLoginFragment() {
        if(existingFragment != new LoginFragment()) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.login_fragment_container, new LoginFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            toolbar.setTitle(getString(R.string.login));
        }
        drawer.closeDrawers();
    }

    @Override
    public void openNewAccountFragment() {
        if(existingFragment != new NewAccountFragment()) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.login_fragment_container, new NewAccountFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            toolbar.setTitle(getString(R.string.new_account));
        }
        drawer.closeDrawers();
    }

    @Override
    public void openSupportFragment() {
        if(existingFragment != new SupportFragment()) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.login_fragment_container, new SupportFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            toolbar.setTitle(getString(R.string.support));
        }
        drawer.closeDrawers();
    }
}
