package bgroseclose.leagueofyou.Models.MatchupModels

import com.google.gson.annotations.SerializedName

class Matchup {
    @SerializedName("lane")
    var lane: String? = null
    @SerializedName("gameId")
    var gameId: Long? = null
    @SerializedName("champion")
    var championId: Int? = null
    @SerializedName("timestamp")
    var timestamp: Long? = null
    @SerializedName("queue")
    var queue: Int? = null
    @SerializedName("role")
    var role: String? = null
    @SerializedName("season")
    var season: Int? = null
}
