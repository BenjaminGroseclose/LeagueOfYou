package bgroseclose.leagueofyou.Models;

public class SummonerInfo {

    //{"id":585897,"accountId":31649572,"name":"RiotSchmick","profileIconId":746,"revisionDate":1538788984000,"summonerLevel":104}
    private int id;
    private int accountId;
    private String name;
    private int profileIconId;
    private long revisionDate;
    private int summonerLevel;

    public SummonerInfo() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(int profileIconId) {
        this.profileIconId = profileIconId;
    }

    public long getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(long revisionDate) {
        this.revisionDate = revisionDate;
    }

    public int getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(int summonerLevel) {
        this.summonerLevel = summonerLevel;
    }
}
