package com.example.cis400finalprojectantranikmedina

import android.app.PendingIntent.getActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import com.firebase.client.Firebase
import com.firebase.ui.database.paging.FirebaseDataSource
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_create_bet.*
import kotlinx.android.synthetic.main.activity_live_bets.*
import kotlinx.android.synthetic.main.fragment_mlbet_create.*
import kotlinx.android.synthetic.main.activity_live_bets.toolbar as toolbar1

class CreateBet : AppCompatActivity() {


    var betType = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_bet)
        setSupportActionBar(toolbar)
        var makeBetButton = findViewById<Button>(R.id.createBetBtn)


        makeBetButton.setOnClickListener {
            val intent = Intent(this, BetInput::class.java)
            intent.putExtra("spinnerItem", betType)
            startActivity(intent)
        }

        val bets = arrayOf("Money Line", "Point Spread", "Over/Under")
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, bets)

        betSpinner.adapter = arrayAdapter


        betSpinner.onItemSelectedListener = object :


        /*
        val spinner: Spinner = findViewById(R.id.betSpinner)

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.bets_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
            spinner.onItemClickListener
        }

         */



            AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
                random.text = "Nothing"
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //To change body of created functions use File | Settings | File Templates.
                betType = bets[position]
                random.text = betType

            }

            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
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
