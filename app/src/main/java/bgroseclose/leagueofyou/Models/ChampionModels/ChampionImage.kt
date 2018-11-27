package bgroseclose.leagueofyou.Models.ChampionModels

import com.google.gson.annotations.SerializedName

class ChampionImage {

    @SerializedName("full")
    var full: String? = null
    @SerializedName("sprite")
    var sprite: String? = null
    @SerializedName("group")
    var group: String? = null

}
