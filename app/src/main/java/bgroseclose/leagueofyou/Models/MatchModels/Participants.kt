package bgroseclose.leagueofyou.Models.MatchModels

import com.google.gson.annotations.SerializedName

class Participants {

    @SerializedName("stats")
    var stats: Stats? = null
    @SerializedName("spell1Id")
    var spell1Id: Int? = null
    @SerializedName("participantId")
    var participantId: Int? = null
    @SerializedName("highestAchievedSeasonTier")
    var highestAchievedSeasonTier: String? = null
    @SerializedName("spell2Id")
    var spell2Id: Int? = null
    @SerializedName("teamId")
    var teamId: Int? = null
    @SerializedName("timeline")
    var timeline: TimeLine? = null
    @SerializedName("championId")
    var championId: Int? = null

}
