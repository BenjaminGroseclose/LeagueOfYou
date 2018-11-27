package bgroseclose.leagueofyou.Models.ChampionModels

import com.google.gson.annotations.SerializedName

class ChampionSkills {

    @SerializedName("attack")
    var attack: Int? = null
    @SerializedName("defense")
    var defense: Int? = null
    @SerializedName("magic")
    var magic: Int? = null
    @SerializedName("difficulty")
    var difficulty: Int? = null

}
