package bgroseclose.leagueofyou.Helpers

fun Float.convertToPercent() : String {
    return this.times(100).toString() + "%"
}