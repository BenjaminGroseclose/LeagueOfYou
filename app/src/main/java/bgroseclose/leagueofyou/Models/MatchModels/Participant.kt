package bgroseclose.leagueofyou.Models.MatchModels

import bgroseclose.leagueofyou.LeagueOfYouSingleton
import bgroseclose.leagueofyou.Models.ChampionModels.ChampionsModel
import com.google.gson.annotations.SerializedName

class Participant {

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

    fun getChampionName() : String {
        var retval = "Unknown"

        for (champion: ChampionsModel in LeagueOfYouSingleton.getChampions()) {
            if(champion.id == championId) {
                return champion.name!!
            }
        }

        return retval
    }


}
