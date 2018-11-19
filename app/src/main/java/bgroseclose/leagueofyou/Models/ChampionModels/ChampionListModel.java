package bgroseclose.leagueofyou.Models.ChampionModels;

public class ChampionListModel {

    private String key;
    private String name;
    private String title;

    public String getId() {
        return key;
    }

    public void setId(String key) {
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
}
