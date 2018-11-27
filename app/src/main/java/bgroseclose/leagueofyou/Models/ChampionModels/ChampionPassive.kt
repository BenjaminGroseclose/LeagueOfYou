package bgroseclose.leagueofyou.Models.ChampionModels

import com.google.gson.annotations.SerializedName

class ChampionPassive {

    @SerializedName("name")
    var name: String? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("image")
    var image: ChampionImage? = null

}
