package com.example.cis400finalprojectantranikmedina

data class psBet(
    val id: String?="",
    val teamBetOn: String="",
    val spread: String="",
    val odds: String="",
    val teamBetAgainst: String="",
    val source: String="",
    val wager: Int = 0,
    val toWin: Int = 0,
    var didWin: Boolean = false,
    var liveBet: Boolean = true
)