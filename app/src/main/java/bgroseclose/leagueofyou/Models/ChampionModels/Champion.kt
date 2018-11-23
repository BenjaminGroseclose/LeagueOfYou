package bgroseclose.leagueofyou.Models.ChampionModels

import com.google.gson.annotations.SerializedName

import java.util.ArrayList

import bgroseclose.leagueofyou.LeagueOfYouSingleton

class Champion {

    @SerializedName("version")
    var version: String? = null
    @SerializedName("key")
    var key: String? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("blurb")
    var blurb: String? = null
    @SerializedName("info")
    var info: ChampionInfo? = null
    @SerializedName("tags")
    var tags: ArrayList<String>? = null
    @SerializedName("partype")
    var partype: String? = null
    @SerializedName("stats")
    var stats: ChampionStats? = null

    var skins: ArrayList<ChampionSkins>? = null


    // http://ddragon.leagueoflegends.com/cdn/6.24.1/img/champion/Aatrox.png
    val championSquare: String
        get() = LeagueOfYouSingleton.riotStaticBaseUrl + "cdn/" + LeagueOfYouSingleton.getCurrentVersionNumber() + "/img/champion/" + name + ".png"

}
