package bgroseclose.leagueofyou.Models

import com.google.gson.annotations.SerializedName

class CounterChampion {
    @SerializedName("championId")
    var id: String? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("winRate")
    var winRate: Float? = null
    @SerializedName("version")
    var version: String? = null
}