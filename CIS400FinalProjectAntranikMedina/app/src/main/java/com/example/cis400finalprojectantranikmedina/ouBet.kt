package com.example.cis400finalprojectantranikmedina

data class ouBet(
    val id: String? ="",
    val teamOne: String ="",
    val teamTwo: String="",
    val overUnder: String="",
    val odds: String="",
    val line: String="",
    val source: String="",
    val wager: Int=0,
    val toWin: Int=0,
    var didWin: Boolean=false,
    var liveBet: Boolean=true
)