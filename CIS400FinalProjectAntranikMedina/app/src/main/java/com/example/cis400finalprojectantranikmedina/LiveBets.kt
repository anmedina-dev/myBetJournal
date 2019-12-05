package com.example.cis400finalprojectantranikmedina

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_create_bet.*
import kotlinx.android.synthetic.main.activity_live_bets.*
import kotlinx.android.synthetic.main.activity_create_bet.toolbar as toolbar1

class LiveBets : AppCompatActivity() {

    lateinit var mlRecyclerView: RecyclerView
    lateinit var mlDatabase: DatabaseReference
    var betType = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_bets)
        setSupportActionBar(toolbar)


        val bets = arrayOf("Money Line", "Point Spread", "Over/Under")
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, bets)

        liveFragSpinner.adapter = arrayAdapter


        liveFragSpinner.onItemSelectedListener = object :





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

        var f:mlLiveFrag= mlLiveFrag()
        replaceFragment(f)

        liveFragSwitch.setOnClickListener {
            if(betType=="Money Line"){
                var f:mlLiveFrag= mlLiveFrag()
                replaceFragment(f)
            }
            if(betType=="Point Spread"){
                var f:psFragLive = psFragLive()
                replaceFragment(f)
            }
            if(betType=="Over/Under"){
                var f:ouFragLive= ouFragLive()
                replaceFragment(f)
            }
        }



    }

    fun replaceFragment(fragment: Fragment){
        val manager=supportFragmentManager
        val transaction=manager.beginTransaction()
        //Check this out for later might have to be fg1, check the contain_main.xml and see
        transaction.replace(R.id.fragLive,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
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
