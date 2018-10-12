package bgroseclose.leagueofyou.Presenters;

public class LoginFragmentPresenter {

    View view;

    public LoginFragmentPresenter(View view) {
        this.view = view;
    }

    public void loginUser(String username, String password, boolean isSaveUsernameToggled) {
        // no need to auth login here. Firebase Auth will do it automatically.
        if(!username.isEmpty() && !password.isEmpty()) {
            if(attemptLogin()) {
                view.loginSuccess();
                if(isSaveUsernameToggled)
                    view.saveUsername(username);
            } else {
                view.loginFailed();
            }

        } else {
            view.invalidUsernameOrPassword();
        }
    }

    // todo: may return boolean here.
    private boolean attemptLogin() {

        return false;
    }

    public interface View {
        void invalidUsernameOrPassword();
        void saveUsername(String username);
        void loginSuccess();
        void loginFailed();
    }
}
