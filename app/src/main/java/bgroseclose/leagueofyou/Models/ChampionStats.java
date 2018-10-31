package bgroseclose.leagueofyou.Models;

import com.google.gson.annotations.SerializedName;

class ChampionStats {

    @SerializedName("hp")
    private double hp;
    @SerializedName("hpperlevel")
    private int hpPerLevel;
    @SerializedName("mp")
    private double mana;
    @SerializedName("mpperlevel")
    private int manaPerLevel;
    @SerializedName("movespeed")
    private int movementSpeed;
    @SerializedName("armor")
    private double armor;
    @SerializedName("armorperlevel")
    private double armorPerLevel;
    @SerializedName("spellblock")
    private double magicResistance;
    @SerializedName("spellblockperlevel")
    private double magicResistancePerLevle;
    @SerializedName("hpregen")
    private double hpRegen;
    @SerializedName("hpregenperlevel")
    private double hpRegenPerLevel;
    @SerializedName("mpregen")
    private double manaRegen;
    @SerializedName("mpregenperlevel")
    private double manaRegenPerLevel;
    @SerializedName("crit")
    private int crit;
    @SerializedName("critperlevel")
    private int critPerLevel;
    @SerializedName("attackDamage")
    private double attackDamage;
    @SerializedName("attackdamageperlevel")
    private double attackDamagePerLevel;
    @SerializedName("attackspeedoffset")
    private double attackSpeedOffSet;
    @SerializedName("attackspeedperlevel")
    private double attackSpeedPerLevel;

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public int getHpPerLevel() {
        return hpPerLevel;
    }

    public void setHpPerLevel(int hpPerLevel) {
        this.hpPerLevel = hpPerLevel;
    }

    public double getMana() {
        return mana;
    }

    public void setMana(double mana) {
        this.mana = mana;
    }

    public int getManaPerLevel() {
        return manaPerLevel;
    }

    public void setManaPerLevel(int manaPerLevel) {
        this.manaPerLevel = manaPerLevel;
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public double getArmor() {
        return armor;
    }

    public void setArmor(double armor) {
        this.armor = armor;
    }

    public double getArmorPerLevel() {
        return armorPerLevel;
    }

    public void setArmorPerLevel(double armorPerLevel) {
        this.armorPerLevel = armorPerLevel;
    }

    public double getMagicResistance() {
        return magicResistance;
    }

    public void setMagicResistance(double magicResistance) {
        this.magicResistance = magicResistance;
    }

    public double getMagicResistancePerLevle() {
        return magicResistancePerLevle;
    }

    public void setMagicResistancePerLevle(double magicResistancePerLevle) {
        this.magicResistancePerLevle = magicResistancePerLevle;
    }

    public double getHpRegen() {
        return hpRegen;
    }

    public void setHpRegen(double hpRegen) {
        this.hpRegen = hpRegen;
    }

    public double getHpRegenPerLevel() {
        return hpRegenPerLevel;
    }

    public void setHpRegenPerLevel(double hpRegenPerLevel) {
        this.hpRegenPerLevel = hpRegenPerLevel;
    }

    public double getManaRegen() {
        return manaRegen;
    }

    public void setManaRegen(double manaRegen) {
        this.manaRegen = manaRegen;
    }

    public double getManaRegenPerLevel() {
        return manaRegenPerLevel;
    }

    public void setManaRegenPerLevel(double manaRegenPerLevel) {
        this.manaRegenPerLevel = manaRegenPerLevel;
    }

    public int getCrit() {
        return crit;
    }

    public void setCrit(int crit) {
        this.crit = crit;
    }

    public int getCritPerLevel() {
        return critPerLevel;
    }

    public void setCritPerLevel(int critPerLevel) {
        this.critPerLevel = critPerLevel;
    }

    public double getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(double attackDamage) {
        this.attackDamage = attackDamage;
    }

    public double getAttackDamagePerLevel() {
        return attackDamagePerLevel;
    }

    public void setAttackDamagePerLevel(double attackDamagePerLevel) {
        this.attackDamagePerLevel = attackDamagePerLevel;
    }

    public double getAttackSpeedOffSet() {
        return attackSpeedOffSet;
    }

    public void setAttackSpeedOffSet(double attackSpeedOffSet) {
        this.attackSpeedOffSet = attackSpeedOffSet;
    }

    public double getAttackSpeedPerLevel() {
        return attackSpeedPerLevel;
    }

    public void setAttackSpeedPerLevel(double attackSpeedPerLevel) {
        this.attackSpeedPerLevel = attackSpeedPerLevel;
    }
}
