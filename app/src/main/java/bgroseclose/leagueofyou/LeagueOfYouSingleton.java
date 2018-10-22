package bgroseclose.leagueofyou;

import bgroseclose.leagueofyou.Models.Account;

public class LeagueOfYouSingleton {

    private static Account account;

    public static Account getAccount() {
        return account;
    }

    public static void setAccount(Account account) {
        LeagueOfYouSingleton.account = account;
    }
}
