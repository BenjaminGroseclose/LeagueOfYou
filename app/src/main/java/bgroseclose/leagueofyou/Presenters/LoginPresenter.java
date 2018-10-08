package bgroseclose.leagueofyou.Presenters;

import android.util.Log;
import android.widget.Toast;

import bgroseclose.leagueofyou.Activites.LoginActivity;
import bgroseclose.leagueofyou.R;

public class LoginPresenter {

    private View view;

    public LoginPresenter(View view) {
        this.view = view;
    }

    public void drawerItemSelected(int id) {
        if(id == R.id.drawer_login_menu) {

        } else if (id == R.id.drawer_new_account_menu) {

        }
    }

    public interface View {
        void openDrawer();
        void openLoginFragment();
        void openNewAccountFragment();
    }

}
