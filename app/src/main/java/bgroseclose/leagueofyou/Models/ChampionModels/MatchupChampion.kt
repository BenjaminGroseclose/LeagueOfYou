package bgroseclose.leagueofyou.Models.ChampionModels

import com.google.gson.annotations.SerializedName

class MatchupChampion {

    @SerializedName("ChampionId")
    var championId: String? = null
    @SerializedName("ChampionName")
    var championName: String? = null
    @SerializedName("championWinRate")
    var championWinRate: Float? = null
}