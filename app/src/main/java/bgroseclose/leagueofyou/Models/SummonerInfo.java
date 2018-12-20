package bgroseclose.leagueofyou.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SummonerInfo {

    private int profileIconId;
    private String name;
    private long summonerLevel;
    private long revisionDate;
    @SerializedName("id")
    private String summonerId;
    private String accountId;
    private List<SummonerRankInfo> summonerRankedInfo;

    public SummonerInfo() {

    }

    public int getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(int profileIconId) {
        this.profileIconId = profileIconId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(long summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public long getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(long revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public List<SummonerRankInfo> getSummonerRankedInfo() {
        return summonerRankedInfo;
    }

    public void setSummonerRankedInfo(List<SummonerRankInfo> summonerRankedInfo) {
        this.summonerRankedInfo = summonerRankedInfo;
    }

}
