package com.example.cis400finalprojectantranikmedina

class mlBet(
    val id: String? = "",
    val teamBetOn: String = "",
    val odds: String = "",
    val teamBetAgainst: String = "",
    val source: String = "",
    val wager: Int = 0,
    val toWin: Int = 0,
    var didWin: Boolean = false,
    var liveBet: Boolean = true
)
