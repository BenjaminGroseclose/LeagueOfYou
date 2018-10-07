package bgroseclose.leagueofyou.Activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import bgroseclose.leagueofyou.Presenters.LoginPresenter;
import bgroseclose.leagueofyou.R;

public class LoginActivity extends AppCompatActivity implements LoginPresenter.View {

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenter(this);
        initToolbar();

    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_drawer);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.info) {
            Toast.makeText(this, "opening Info", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public void openDrawer() {
        Toast.makeText(this, "opening Drawer", Toast.LENGTH_SHORT).show();
    }
}
