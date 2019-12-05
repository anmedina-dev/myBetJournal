package com.example.cis400finalprojectantranikmedina

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.android.synthetic.main.bet_type_item.view.*
import java.io.Serializable
import java.text.FieldPosition


data class BetTypesList (
    @SerializedName ("betTitle") val betTitle: String?,
    @SerializedName("betDescription") val betDescription: String?
): Serializable

val betTypes = """
    [
    {
    "index":0,
    "betTitle": "MoneyLine"
    "betDescription": "A win bet is one of the most popular wagers that can be placed, partially because it’s so easy to understand and partially because it’s considered the “traditional” way to bet on several sports. This wager can be used in virtually every sport we can bet on, and it involves simply picking who is going to win a game, match, or other event."
        
    },
    {
    "index":1,
    "betTitle": "Over/Under"
    "betDescription": "An over–under or over/under (O/U) bet is a wager in which a sportsbook will predict a number for a statistic in a given game (usually the combined score of the two teams), and bettors wager that the actual number in the game will be either higher or lower than that number."
        
    },
    {
    "index":2,
    "betTitle": "Point Spread"
    "betDescription": "Spread betting is any of various types of wagering on the outcome of an event where the pay-off is based on the accuracy of the wager, rather than a simple "win or lose" outcome, such as fixed-odds (or money-line) betting or parimutuel betting. "
        
    }
    ]
""".trimMargin()