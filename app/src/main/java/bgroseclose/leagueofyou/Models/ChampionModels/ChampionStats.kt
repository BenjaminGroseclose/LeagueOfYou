package bgroseclose.leagueofyou.Models.ChampionModels

import com.google.gson.annotations.SerializedName

class ChampionStats {

    @SerializedName("hp")
    var health: Float? = null
    @SerializedName("hpperlevel")
    var healthPerLevel: Float? = null
    @SerializedName("mp")
    var mana: Float? = null
    @SerializedName("mpperlevel")
    var manaPerLevel: Float? = null
    @SerializedName("movespeed")
    var movementSpeed: Float? = null
    @SerializedName("armor")
    var armor: Float? = null
    @SerializedName("armorperlevel")
    var armorPerLevel: Float? = null
    @SerializedName("spellblock")
    var magicResistance: Float? = null
    @SerializedName("spellblockperlevel")
    var magicResistancePerLevel: Float? = null
    @SerializedName("attackrange")
    var attackRange: Float? = null
    @SerializedName("hpregen")
    var healthRegen: Float? = null
    @SerializedName("hpregenperlevel")
    var healthRegenPerLevel: Float? = null
    @SerializedName("mpregen")
    var manaRegen: Float? = null
    @SerializedName("mpregenperlevel")
    var manaRegenPerLevel: Float? = null
    @SerializedName("crit")
    var crit: Float? = null
    @SerializedName("critperlevel")
    var critPerLevel: Float? = null
    @SerializedName("attackdamage")
    var attackDamage: Float? = null
    @SerializedName("attackdamageperlevel")
    var attackDamagePerLevel: Float? = null

    // Offset in relation to 1 hit per second I believe.
    @SerializedName("attackspeedoffset")
    var attackSpeedOffSet: Float? = null
    @SerializedName("attackspeedperlevel")
    var attackSpeedPerLevel: Float? = null


}