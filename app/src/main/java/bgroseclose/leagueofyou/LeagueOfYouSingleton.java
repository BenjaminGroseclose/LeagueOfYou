package bgroseclose.leagueofyou;

import bgroseclose.leagueofyou.Models.SummonerInfo;

public class LeagueOfYouSingleton {

    private static SummonerInfo summonerInfo;

    public static SummonerInfo getSummonerInfo() {
        return summonerInfo;
    }

    public static void setSummonerInfo(SummonerInfo summonerInfo) {
        LeagueOfYouSingleton.summonerInfo = summonerInfo;
    }
}
