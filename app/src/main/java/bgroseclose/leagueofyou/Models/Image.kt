package bgroseclose.leagueofyou.Models

import com.google.gson.annotations.SerializedName

class Image {

    @SerializedName("full")
    var full: String? = null
    @SerializedName("sprite")
    var sprite: String? = null
    @SerializedName("group")
    var group: String? = null

}
