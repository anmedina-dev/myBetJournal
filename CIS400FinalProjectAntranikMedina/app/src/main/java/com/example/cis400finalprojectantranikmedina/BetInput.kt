package com.example.cis400finalprojectantranikmedina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_bet_input.*

class BetInput : AppCompatActivity() {

    lateinit var teamBetOnML: String
    lateinit var oddsML: String
    lateinit var teamBetAgainstML: String
    lateinit var wagerML: String
    lateinit var toWinML: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bet_input)

        val betType = intent.getStringExtra("spinnerItem")

        betInputTitle.text = "Create " + betType + " Bet"

        if(betType == "Money Line"){
            var f:MLBetCreate = MLBetCreate()
            replaceFragment(f)

        }
        if (betType =="Point Spread"){
            var f:PSBetCreate = PSBetCreate()
            replaceFragment(f)
        }
        if(betType == "Over/Under"){
            var f:OUBetCreate = OUBetCreate()
            replaceFragment(f)
        }


    }

    fun replaceFragment(fragment: Fragment){
        val manager=supportFragmentManager
        val transaction=manager.beginTransaction()
        //Check this out for later might have to be fg1, check the contain_main.xml and see
        transaction.replace(R.id.fg2,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


}
