package bgroseclose.leagueofyou;

import android.content.Context;
import android.net.ConnectivityManager;

import java.util.Objects;

import bgroseclose.leagueofyou.Models.LeagueOfYouAccount;
import bgroseclose.leagueofyou.Models.SummonerRankInfo;

public class LeagueOfYouSingleton {

    // todo: needs to be updated every 24 hours.
    public static final String riotKet = "RGAPI-446f4a93-41d8-4db7-876c-7d18457d46e0";
    public static final String riotBaseUrl = "https://na1.api.riotgames.com/lol/";
    public static final String riotStaticBaseUrl = "https://ddragon.leagueoflegends.com/";
    private static LeagueOfYouAccount leagueOfYouAccount;

    public static String getCurrentVersionNumber() {
        return currentVersionNumber;
    }

    public static void setCurrentVersionNumber(String currentVersionNumber) {
        LeagueOfYouSingleton.currentVersionNumber = currentVersionNumber;
    }

    public static String getSummonerProfileIcon() {
        return riotStaticBaseUrl.concat("cdn/")
                                .concat(getCurrentVersionNumber())
                                .concat("/img/profileicon/")
                                .concat(String.valueOf(getLeagueOfYouAccount().getSummonerInfo().getProfileIconId()))
                                .concat(".png");
    }

    public static String getChampionIcon(String name) {
        return riotStaticBaseUrl.concat("cdn/")
                                .concat(getCurrentVersionNumber())
                                .concat("/img/champion/")
                                .concat(name)
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
        ConnectivityManager contextSystemService = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return Objects.requireNonNull(contextSystemService).getActiveNetworkInfo() != null;
    }

    public static int getSoloQueue() {
        int retval = -1;
        LeagueOfYouAccount account = getLeagueOfYouAccount();
        for (int i = 0; i < account.getSummonerInfo().getSummonerRankedInfo().size(); i++) {
            SummonerRankInfo rank = account.getSummonerInfo().getSummonerRankedInfo().get(i);
            if(rank.getQueueType().equals("RANKED_SOLO_5x5")) {
                retval = i;
            }
        }
        return retval;
    }

    public static class ErrorConstants {
        public static final String EMAIL_ALREADY_EXISTS = "The email address is already in use by another account.";
    }

    public class Constants {
        // Extras
        public static final String CHAMPION_NAME_EXTRA = "CHAMPION_NAME_EXTRA";
        public static final int CHAMPION_PAGE_COUNT = 3;
    }


}
