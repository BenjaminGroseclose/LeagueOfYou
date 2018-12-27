package bgroseclose.leagueofyou.Models.MatchModels

import com.google.gson.annotations.SerializedName

class Player {

    @SerializedName("currentPlatformId")
    var currentPlatformId: String? = null
    @SerializedName("summonerName")
    var summonerName: String? = null
    @SerializedName("matchHistoryUri")
    var matchHistoryUri: String? = null
    @SerializedName("platformId")
    var platformId: String? = null
    @SerializedName("currentAccountId")
    var currentAccountId: String? = null
    @SerializedName("profileIcon")
    var profileIcon: Int? = null
    @SerializedName("summonerId")
    var summonerId: String? = null
    @SerializedName("accountId")
    var accountId: String? = null

}
