package bgroseclose.leagueofyou.Presenters;

public class LoginFragmentPresenter {

    View view;

    public LoginFragmentPresenter(View view) {
        this.view = view;
    }

    public void loginUser(String username, String password) {
        if(!username.isEmpty() && !password.isEmpty()) {
            attemptLogin();
        } else {
            view.blankUsernameOrPassword();
        }
    }

    // todo: may return boolean here.
    private void attemptLogin() {

    }

    public interface View {
        void blankUsernameOrPassword();
        void loginSuccess();
        void loginFailed();
    }
}
