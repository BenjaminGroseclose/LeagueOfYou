package bgroseclose.leagueofyou.Models.MatchModels

import com.google.gson.annotations.SerializedName

class Teams {

    @SerializedName("firstDragon")
    var firstDragon: Boolean? = null
    @SerializedName("bans")
    var bans: List<Ban>? = null
    @SerializedName("firstInhibitor")
    var firstInhibitor: Boolean? = null
    @SerializedName("win")
    var win: String? = null
    @SerializedName("firstRiftHerald")
    var firstRiftHerald: Boolean? = null
    @SerializedName("firstBaron")
    var firstBaron: Boolean? = null
    @SerializedName("baronKills")
    var baronKills: Int? = null
    @SerializedName("riftHeraldKills")
    var riftHeraldKills: Int? = null
    @SerializedName("firstBlood")
    var firstBlood: Boolean? = null
    // 100 for blue side. 200 for red side.
    @SerializedName("teamId")
    var teamId: Int? = null
    @SerializedName("firstTower")
    var firstTower: Boolean? = null
    @SerializedName("vilemawKills")
    var vilemawKills: Int? = null
    @SerializedName("inhibitorKills")
    var inhibitorKills: Int? = null
    @SerializedName("towerKills")
    var towerKills: Int? = null
    @SerializedName("dominionVictoryScore")
    var dominionVictoryScore: Int? = null
    @SerializedName("dragonKills")
    var dragonKills: Int? = null
}

class Ban {
    @SerializedName("pickTurn")
    var pickTurn: Int? = null
    @SerializedName("championId")
    var championId: Int? = null
}

