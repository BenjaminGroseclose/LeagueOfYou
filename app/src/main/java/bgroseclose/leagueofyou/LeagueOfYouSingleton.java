package bgroseclose.leagueofyou;

import android.content.Context;
import android.net.ConnectivityManager;

import bgroseclose.leagueofyou.Models.LeagueOfYouAccount;

public class LeagueOfYouSingleton {

    // todo: needs to be updated every 24 hours.
    public static final String riotKet = "RGAPI-e0bf3354-acc9-44ca-9cab-77b15b5b3f8a";
    public static final String riotBaseUrl = "https://na1.api.riotgames.com/lol/";
    public static final String riotStaticBaseUrl = "https://ddragon.leagueoflegends.com/";
    private static LeagueOfYouAccount leagueOfYouAccount;

    public static String getCurrentVersionNumber() {
        return currentVersionNumber;
    }

    public static void setCurrentVersionNumber(String currentVersionNumber) {
        LeagueOfYouSingleton.currentVersionNumber = currentVersionNumber;
    }

    //http://ddragon.leagueoflegends.com/cdn/6.24.1/img/profileicon/588.png

    public static String getSummonerProfileIcon() {
        return riotStaticBaseUrl.concat("cdn/")
                                .concat(getCurrentVersionNumber())
                                .concat("/img/profileicon/")
                                .concat(String.valueOf(getLeagueOfYouAccount().getSummonerInfo().getProfileIconId()))
                                .concat(".png");
    }

    private static String currentVersionNumber;

    public static LeagueOfYouAccount getLeagueOfYouAccount() {
        return leagueOfYouAccount;
    }

    public static void setLeagueOfYouAccount(LeagueOfYouAccount leagueOfYouAccount) {
        LeagueOfYouSingleton.leagueOfYouAccount = leagueOfYouAccount;
    }

    public static boolean checkConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public static class ErrorConstants {

        public static final String EMAIL_ALREADY_EXISTS = "The email address is already in use by another account.";
    }
}
