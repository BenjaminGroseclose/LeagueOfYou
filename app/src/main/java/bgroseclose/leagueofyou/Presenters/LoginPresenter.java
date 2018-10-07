package bgroseclose.leagueofyou.Presenters;

public class LoginPresenter {

    private View view;

    public LoginPresenter(View view) {
        this.view = view;
    }

    public interface View {
        void openDrawer();
    }

}
