package bgroseclose.leagueofyou.Models.ChampionModels

import com.google.gson.annotations.SerializedName

class ChampionsModel {

    @SerializedName("key")
    var key: String? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("id")
    var id: Int? = null
}