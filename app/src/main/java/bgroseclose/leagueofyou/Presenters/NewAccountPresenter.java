package bgroseclose.leagueofyou.Presenters;

import bgroseclose.leagueofyou.Models.NewAccount;

public class NewAccountPresenter {

    private View view;

    public NewAccountPresenter(View view) {
        this.view = view;
    }

    public void createNewAccount(NewAccount account) {
    }

    public interface View {

    }

}
