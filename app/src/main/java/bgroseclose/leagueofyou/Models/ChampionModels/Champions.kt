package bgroseclose.leagueofyou.Models.ChampionModels

import java.util.HashMap

class Champions {

    var championList: HashMap<String, ChampionsModel>? = null
}

class ChampionsModel {

    var id: String? = null
    var name: String? = null
    var title: String? = null
}