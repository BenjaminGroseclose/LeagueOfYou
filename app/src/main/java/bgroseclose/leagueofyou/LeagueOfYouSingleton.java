package bgroseclose.leagueofyou;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v4.content.ContextCompat;

import bgroseclose.leagueofyou.Models.Account;

public class LeagueOfYouSingleton {

    // todo: needs to be updated every 24 hours.
    public static final String riotKet = "RGAPI-9dfc29d1-9029-49ae-8ab6-0cbece05b397";
    public static final String riotBaseUrl = "https://na1.api.riotgames.com/lol/";
    public static final String riotStaticBaseUrl = "https://ddragon.leagueoflegends.com/";
    private static Account account;

    public static String getCurrentVersionNumber() {
        return currentVersionNumber;
    }

    public static void setCurrentVersionNumber(String currentVersionNumber) {
        LeagueOfYouSingleton.currentVersionNumber = currentVersionNumber;
    }

    private static String currentVersionNumber;

    public static Account getAccount() {
        return account;
    }

    public static void setAccount(Account account) {
        LeagueOfYouSingleton.account = account;
    }

    public static boolean checkConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public static class ErrorConstants {

        public static final String EMAIL_ALREADY_EXISTS = "The email address is already in use by another account.";
    }
}
