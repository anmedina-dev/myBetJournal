package com.example.cis400finalprojectantranikmedina

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_betting_history.*

class BettingHistory : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_betting_history)
        setSupportActionBar(toolbar)

        var f1:MLHistoryFrag= MLHistoryFrag()
        var f2:PSHistoryFrag= PSHistoryFrag()
        var f3:OUHistoryFrag= OUHistoryFrag()
        replaceFragment(f1,f2,f3)
    }

    fun replaceFragment(fragment1: Fragment, fragment2: Fragment, fragment3:Fragment){
        val manager=supportFragmentManager
        val transaction=manager.beginTransaction()
        //Check this out for later might have to be fg1, check the contain_main.xml and see
        transaction.replace(R.id.mlHistoryFragItem,fragment1)
        transaction.addToBackStack(null)
        transaction.replace(R.id.psHistoryFragItem,fragment2)
        transaction.addToBackStack(null)
        transaction.replace(R.id.ouHistoryFragItem,fragment3)
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
