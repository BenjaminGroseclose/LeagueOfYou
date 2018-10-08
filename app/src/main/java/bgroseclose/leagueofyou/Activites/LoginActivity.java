package bgroseclose.leagueofyou.Activites;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import bgroseclose.leagueofyou.Fragments.LoginFragment;
import bgroseclose.leagueofyou.Fragments.NewAccountFragment;
import bgroseclose.leagueofyou.Presenters.LoginPresenter;
import bgroseclose.leagueofyou.R;

public class LoginActivity extends AppCompatActivity implements LoginPresenter.View {

    private LoginPresenter presenter;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenter(this);
        initToolbar();
        initDrawer();

    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.drawable.ic_drawer);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer();
            }
        });
    }

    private void initDrawer() {
        drawer = findViewById(R.id.drawer_login_activity);
        navigationView = findViewById(R.id.login_drawer);
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

    private void openFragment(Fragment fragmentClass) {
        Fragment fragment = fragmentClass;

        if(fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.login_frame_layout, fragment);
            fragmentTransaction.commit();
        }
        drawer.closeDrawers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.info) {
            Toast.makeText(this, "opening Info", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public void openDrawer() {
        drawer.openDrawer(GravityCompat.START);
        Toast.makeText(this, "opening Drawer", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openLoginFragment() {
        openFragment(new LoginFragment());
    }

    @Override
    public void openNewAccountFragment() {
        openFragment(new NewAccountFragment());
    }
}
