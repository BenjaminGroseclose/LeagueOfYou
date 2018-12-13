package bgroseclose.leagueofyou.Models.ChampionModels

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Champion : Serializable{

    @SerializedName("type")
    var type: String? = null
    @SerializedName("format")
    var format: String? = null
    @SerializedName("versino")
    var version: String? = null
    @SerializedName("data")
    var wrapper: ChampionWrapper? = null

}

