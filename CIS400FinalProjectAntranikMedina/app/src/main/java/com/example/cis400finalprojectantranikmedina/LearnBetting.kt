package com.example.cis400finalprojectantranikmedina

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

import kotlinx.android.synthetic.main.activity_learn_betting.*

class LearnBetting : AppCompatActivity(){
    lateinit var  dataList: MutableList<BetTypesList>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_betting)
        setSupportActionBar(toolbar)

        val recyclerView = findViewById(R.id.learnBetRv) as RecyclerView

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val bets = ArrayList<BetTypesList>()

        bets.add(BetTypesList("Money Line", "A win bet is one of the most popular wagers that can be placed, partially because it’s so easy to understand and partially because it’s considered the “traditional” way to bet on several sports. This wager can be used in virtually every sport we can bet on, and it involves simply picking who is going to win a game, match, or other event"))
        bets.add(BetTypesList("Over/Under", "An over–under or over/under (O/U) bet is a wager in which a sportsbook will predict a number for a statistic in a given game (usually the combined score of the two teams), and bettors wager that the actual number in the game will be either higher or lower than that number."))
        bets.add(BetTypesList("Point Spread", "Spread betting is any of various types of wagering on the outcome of an event where the pay-off is based on the accuracy of the wager, rather than a simple \"win or lose\" outcome, such as fixed-odds (or money-line) betting or parimutuel betting. "))


        val adapter = RvAdapter(bets)

        recyclerView.adapter = adapter

    }
    //Functions below are for the menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.Home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.logOut -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> {
                return true
            }
        }
    }


}
