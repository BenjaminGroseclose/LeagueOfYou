package bgroseclose.leagueofyou.Models.ChampionModels

import com.google.gson.annotations.SerializedName

class ChampionWrapper {
    @SerializedName("", alternate = ["Aatrox"])
    var champion: ChampionInfo? = null
}