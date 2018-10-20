package bgroseclose.leagueofyou;

import bgroseclose.leagueofyou.Models.Account;
import bgroseclose.leagueofyou.Models.SummonerInfo;

public class LeagueOfYouSingleton {

    private static Account account;

    public static Account getAccount() {
        return account;
    }

    public static void setAccount(Account account) {
        LeagueOfYouSingleton.account = account;
    }
}
