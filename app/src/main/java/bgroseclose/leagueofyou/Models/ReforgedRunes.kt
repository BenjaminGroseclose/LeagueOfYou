package bgroseclose.leagueofyou.Models

import com.google.gson.annotations.SerializedName

class ReforgedRunes {

    @SerializedName("id")
    var id: Int? = null
    @SerializedName("key")
    var key: String? = null
    @SerializedName("icon")
    var icon: String? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("slots")
    var runes: ArrayList<Runes>? = null

}

class Runes{

    @SerializedName("runes")
    var firstTier: Rune? = null
    @SerializedName("runes")
    var secondTier: Rune? = null
    @SerializedName("runes")
    var thirdTier: Rune? = null
    @SerializedName("runes")
    var fourthTier: Rune? = null

}


class Rune {

    @SerializedName("id")
    var id: Int? = null
    @SerializedName("key")
    var key: String? = null
    @SerializedName("icon")
    var icon: String? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("shortDesc")
    var shortDesc: String? = null

}
