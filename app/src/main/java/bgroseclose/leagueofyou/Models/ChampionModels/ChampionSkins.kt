package bgroseclose.leagueofyou.Models.ChampionModels

import com.google.gson.annotations.SerializedName

class ChampionSkins {

    @SerializedName("id")
    var skinId: Long? = null
    @SerializedName("num")
    var num: Int? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("chromas")
    var chromas: Boolean? = null

}