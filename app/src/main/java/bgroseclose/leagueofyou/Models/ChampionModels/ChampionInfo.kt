package bgroseclose.leagueofyou.Models.ChampionModels

import bgroseclose.leagueofyou.Models.Image
import com.google.gson.annotations.SerializedName

class ChampionInfo {

    @SerializedName("id")
    var id: String? = null
    @SerializedName("key")
    var key: String? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("image")
    var image: Image? = null
    @SerializedName("skins")
    var skins: ArrayList<ChampionSkins>? = null
    @SerializedName("lore")
    var lore: String? = null
    @SerializedName("blurb")
    var blurb: String? = null
    @SerializedName("allytips")
    var allyTips: ArrayList<String>? = null
    @SerializedName("enemytips")
    var enemytips: ArrayList<String>? = null
    @SerializedName("tags")
    var tags: ArrayList<String>? = null
    @SerializedName("partype")
    var partype: String? = null
    @SerializedName("info")
    var info: ChampionSkills? = null
    @SerializedName("stats")
    var stats: ChampionStats? = null
    @SerializedName("spells")
    var spells: ArrayList<ChampionSpell>? = null
    @SerializedName("passive")
    var passive: ChampionPassive? = null
}
