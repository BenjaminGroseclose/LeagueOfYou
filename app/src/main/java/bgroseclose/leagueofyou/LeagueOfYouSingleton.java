package bgroseclose.leagueofyou;

import bgroseclose.leagueofyou.Models.Account;

public class LeagueOfYouSingleton {

    // todo: needs to be updated every 24 hours.
    public static final String riotKet = "";
    public static final String riotBaseUrl = "https://na1.api.riotgames.com/lol/";
    public static final String riotStaticBaseUrl = "http://ddragon.leagueoflegends.com/";
    private static Account account;

    public static Account getAccount() {
        return account;
    }

    public static void setAccount(Account account) {
        LeagueOfYouSingleton.account = account;
    }
}
