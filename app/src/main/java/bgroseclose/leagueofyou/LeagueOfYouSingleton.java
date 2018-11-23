package bgroseclose.leagueofyou;

import android.content.Context;
import android.net.ConnectivityManager;

import bgroseclose.leagueofyou.Models.LeagueOfYouAccount;
import bgroseclose.leagueofyou.Models.SummonerRankInfo;

public class LeagueOfYouSingleton {

    // todo: needs to be updated every 24 hours.
    public static final String riotKet = "RGAPI-be64d6b4-812a-474c-a19c-3afd78d0e5c2";
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

    //http://ddragon.leagueoflegends.com/cdn/8.22.1/img/champion/Aatrox.png
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
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
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
}
