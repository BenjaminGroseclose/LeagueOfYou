package bgroseclose.leagueofyou.Models;

import com.google.gson.annotations.SerializedName;

public class SummonerRankInfo {

    @SerializedName("queueType")
    private String queueType;
    @SerializedName("hotStreak")
    private boolean hotStreak;
    @SerializedName("wins")
    private int wins;
    @SerializedName("veteran")
    private boolean veteran;
    @SerializedName("losses")
    private int losses;
    @SerializedName("playerOrTeamId")
    private long summonerId;
    @SerializedName("leagueName")
    private String leagueName;
    @SerializedName("playerOrTeamName")
    private String inGameName;
    @SerializedName("inactive")
    private boolean inactive;
    @SerializedName("rank")
    private String rank;
    @SerializedName("freshBlood")
    private boolean freshBlood;
    @SerializedName("leagueId")
    private String leagueId;
    @SerializedName("tier")
    private String tier;
    @SerializedName("leaguePoints")
    private int leaguePoints;

    public String getQueueType() {
        return queueType;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    public boolean isHotStreak() {
        return hotStreak;
    }

    public void setHotStreak(boolean hotStreak) {
        this.hotStreak = hotStreak;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public boolean isVeteran() {
        return veteran;
    }

    public void setVeteran(boolean veteran) {
        this.veteran = veteran;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public long getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(long summonerId) {
        this.summonerId = summonerId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getInGameName() {
        return inGameName;
    }

    public void setInGameName(String inGameName) {
        this.inGameName = inGameName;
    }

    public boolean isInactive() {
        return inactive;
    }

    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public boolean isFreshBlood() {
        return freshBlood;
    }

    public void setFreshBlood(boolean freshBlood) {
        this.freshBlood = freshBlood;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public int getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }
}