package bgroseclose.leagueofyou.Models.ChampionModels;

import com.google.gson.annotations.SerializedName;

public class ChampionInfo {

    @SerializedName("attack")
    private int attack;
    @SerializedName("defense")
    private int defense;
    @SerializedName("magic")
    private int magic;
    @SerializedName("difficulty")
    private int difficulty;

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getMagic() {
        return magic;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
