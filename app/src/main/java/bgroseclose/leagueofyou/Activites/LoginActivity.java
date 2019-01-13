package bgroseclose.leagueofyou.Activites;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import bgroseclose.leagueofyou.Fragments.LoginFragment;
import bgroseclose.leagueofyou.Fragments.NewAccountFragment;
import bgroseclose.leagueofyou.Fragments.SupportFragment;
import bgroseclose.leagueofyou.Presenters.Activities.LoginPresenter;
import bgroseclose.leagueofyou.R;

public class LoginActivity extends AppCompatActivity implements LoginPresenter.View {


    public static Fragment existingFragment;

    private LoginPresenter presenter;
    private DrawerLayout drawer;
    private NavigationView navigationView;
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
        if (findViewById(R.id.login_container) != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.login_container, new LoginFragment());
            fragmentTransaction.commit();
            existingFragment = new LoginFragment();
        }
        toolbar.setTitle(getString(R.string.login));
    }

    private void initDrawerAndToolbar() {
        toolbar = findViewById(R.id.login_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        toolbar.setNavigationIcon(R.drawable.ic_drawer);

        drawer = findViewById(R.id.login_drawer_layout);
        navigationView = findViewById(R.id.login_drawer);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        setupDrawerContent(navigationView);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
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
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.login_container);
            if(currentFragment instanceof  LoginFragment) {
                Toast.makeText(this, "Login Fragment", Toast.LENGTH_SHORT).show();
            } else if(currentFragment instanceof NewAccountFragment) {
                Toast.makeText(this, "New Account Fragment", Toast.LENGTH_SHORT).show();
            } else if(currentFragment instanceof SupportFragment) {
                Toast.makeText(this, "Support Fragment", Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }

    @Override
    public void openLoginFragment() {
        if(existingFragment != new LoginFragment()) {
            existingFragment = new LoginFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.login_container, existingFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        drawer.closeDrawers();
    }

    @Override
    public void openNewAccountFragment() {
        if(existingFragment != new NewAccountFragment()) {
            existingFragment = new NewAccountFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.login_container, existingFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        drawer.closeDrawers();
    }

    @Override
    public void openSupportFragment() {
        if(existingFragment != new SupportFragment()) {
            existingFragment = new SupportFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.login_container, existingFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        drawer.closeDrawers();
    }

}
