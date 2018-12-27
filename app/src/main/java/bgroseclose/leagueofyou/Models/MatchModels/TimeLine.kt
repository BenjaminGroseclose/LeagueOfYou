package bgroseclose.leagueofyou.Models.MatchModels

import com.google.gson.annotations.SerializedName

class TimeLine {

    @SerializedName("lane")
    var lane: String? = null
    @SerializedName("participantId")
    var participantId: Int? = null
    @SerializedName("csDiffPerMinDeltas")
    var csDiffPerMinDeltas: PerMinuteDetail? = null
    @SerializedName("goldPerMinDeltas")
    var goldPerMinDeltas: PerMinuteDetail? = null
    @SerializedName("xpDiffPerMinDeltas")
    var xpDiffPerMinDeltas: PerMinuteDetail? = null
    @SerializedName("creepsPerMinDeltas")
    var creepsPerMinDeltas: PerMinuteDetail? = null
    @SerializedName("xpPerMinDeltas")
    var xpPerMinDeltas: PerMinuteDetail? = null
    @SerializedName("role")
    var role: String? = null
    @SerializedName("damageTakenDiffPerMinDeltas")
    var damageTakenDiffPerMinDeltas: PerMinuteDetail? = null
    @SerializedName("damageTakenPerMinDeltas")
    var damageTakenPerMinDeltas: PerMinuteDetail? = null
}

class PerMinuteDetail {
    @SerializedName("0-10")
    var zeroToTen: Float? = null
    @SerializedName("10-20")
    var tenToTwenty: Float? = null
    @SerializedName("20-30")
    var twentyToThirdy: Float? = null
    @SerializedName("40-50")
    var fortyToFifty: Float? = null
    @SerializedName("50-60")
    var fiftyToSixty: Float? = null
}
