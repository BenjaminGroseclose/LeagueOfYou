package bgroseclose.leagueofyou.Models.MatchupModels

import com.google.gson.annotations.SerializedName

class MatchupList {
    @SerializedName("matches")
    var matches: List<Matchup>? = null
    @SerializedName("endIndex")
    var endIndex: Int? = null
    @SerializedName("startIndex")
    var startIndex: Int? = null
    @SerializedName("totalGames")
    var totalGames: Int? = null

}
