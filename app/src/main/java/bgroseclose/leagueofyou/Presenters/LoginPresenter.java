package bgroseclose.leagueofyou.Presenters;

import android.support.v4.app.Fragment;

import bgroseclose.leagueofyou.R;

public class LoginPresenter {

    private View view;

    public LoginPresenter(View view) {
        this.view = view;
    }

    public void drawerItemSelected(int id) {
        if(id == R.id.drawer_login_menu) {
            view.openLoginFragment();
        } else if (id == R.id.drawer_new_account_menu) {
            view.openNewAccountFragment();
        } else if (id == R.id.drawer_support_menu) {
            view.openSupportFragment();
        }
    }

    public interface View {
        void openLoginFragment();
        void openNewAccountFragment();
        void openSupportFragment();
    }

}
