package bgroseclose.leagueofyou.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Champion {

    @SerializedName("version")
    private String version;
    @SerializedName("key")
    private String key;
    @SerializedName("name")
    private String name;
    @SerializedName("title")
    private String title;
    @SerializedName("blurb")
    private String blurb;
    @SerializedName("info")
    private ChampionInfo info;
    @SerializedName("tags")
    private ArrayList<String> tags;
    @SerializedName("partype")
    private String partype;
    @SerializedName("stats")
    private ChampionStats stats;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public ChampionInfo getInfo() {
        return info;
    }

    public void setInfo(ChampionInfo info) {
        this.info = info;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getPartype() {
        return partype;
    }

    public void setPartype(String partype) {
        this.partype = partype;
    }

    public ChampionStats getStats() {
        return stats;
    }

    public void setStats(ChampionStats stats) {
        this.stats = stats;
    }
}
