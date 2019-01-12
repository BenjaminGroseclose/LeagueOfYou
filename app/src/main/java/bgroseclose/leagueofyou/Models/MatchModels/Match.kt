package bgroseclose.leagueofyou.Models.MatchModels

import com.google.gson.annotations.SerializedName

class Match {
    @SerializedName("seasonId")
    var seasonId: Int? = null
    @SerializedName("queueId")
    var queueId: Int? = null
    @SerializedName("gameId")
    var gameId: Long? = null
    @SerializedName("participantIdentities")
    var participantIdentities: List<ParticipantIdentities>? = null
    @SerializedName("gameVersion")
    var gameVersion: String? = null
    @SerializedName("platformId")
    var platformId: String? = null
    @SerializedName("gameMode")
    var gameMode: String? = null
    @SerializedName("mapId")
    var mapId: Int? = null
    @SerializedName("gameType")
    var gameType: String? = null
    @SerializedName("teams")
    var teams: List<Teams>? = null
    @SerializedName("participants")
    var participants: List<Participant>? = null
    @SerializedName("gameDuration")
    var gameDuration: Long? = null
    @SerializedName("gameCreation")
    var gameCreation: Long? = null

}
