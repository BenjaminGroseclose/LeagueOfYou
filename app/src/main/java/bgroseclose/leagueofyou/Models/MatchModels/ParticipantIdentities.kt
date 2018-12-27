package bgroseclose.leagueofyou.Models.MatchModels

import com.google.gson.annotations.SerializedName

class ParticipantIdentities {

    @SerializedName("player")
    var player: Player? = null
    @SerializedName("participantId")
    var participantId: Int? = null

}
