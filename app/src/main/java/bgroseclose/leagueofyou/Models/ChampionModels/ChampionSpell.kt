package bgroseclose.leagueofyou.Models.ChampionModels

import bgroseclose.leagueofyou.Models.Image
import com.google.gson.annotations.SerializedName

class ChampionSpell {

    @SerializedName("id")
    var id: String? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("image")
    var image: Image? = null
    @SerializedName("resource")
    var resource: String? = null

}
