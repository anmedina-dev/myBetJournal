package com.example.cis400finalprojectantranikmedina

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_oubet_create.*


class OUBetCreate : Fragment() {
    val ou = arrayOf("Over", "Under")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_oubet_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        submitBetOU.setOnClickListener {
            var teamOneOU = teamOneOUet.text.toString().trim()
            var teamTwoOU = teamTwoOUet.text.toString().trim()
            var oddsOU = oddsBetOUet.text.toString().trim()
            var wagerOU = wagerBetOUet.text.toString().trim()
            var lineOU = lineOUet.text.toString().trim()
            var toWinOU = toWinOUet.text.toString().trim()
            var sourceOU = sourceBetOUet.text.toString().trim()
            var choiceOU = choiceOUet.text.toString().trim()
            saveMLBet(teamOneOU, teamTwoOU, oddsOU, wagerOU, toWinOU, sourceOU, lineOU, choiceOU)
            val intent = Intent(getActivity(), MainActivity::class.java)
            startActivity(intent)
        }



    }

    private fun saveMLBet(teamOneOU: String, teamTwoOU: String, oddsOU: String,wagerOU: String, toWinOU: String, sourceOU: String, lineOU: String, choiceOU: String) {
        val teamOneOU = teamOneOU
        val teamTwoOU = teamTwoOU
        val oddsFB = oddsOU
        var wagerFBString = wagerOU
        var toWinFBString = toWinOU
        val sourceFB = sourceOU
        val lineFB = lineOU
        val choiceFB = choiceOU

        if (teamOneOU.isEmpty()) {
            Toast.makeText(getActivity(), "Please enter a Team One", Toast.LENGTH_LONG).show()
            return
        }
        if (teamTwoOU.isEmpty()) {
            Toast.makeText(getActivity(), "Please enter a Team Two", Toast.LENGTH_LONG).show()
            return
        }
        if (oddsFB.isEmpty()) {
            Toast.makeText(getActivity(), "Please enter your odds", Toast.LENGTH_LONG).show()
            return
        }
        if (wagerFBString.isEmpty()) {
            Toast.makeText(getActivity(), "Please enter a wager", Toast.LENGTH_LONG).show()
            return
        }
        if (toWinFBString.isEmpty()) {
            Toast.makeText(getActivity(), "Please enter the predicted profits", Toast.LENGTH_LONG).show()
            return
        }
        if (sourceFB.isEmpty()) {
            Toast.makeText(getActivity(), "Please enter the sportsbook", Toast.LENGTH_LONG).show()
            return
        }
        if (lineFB.isEmpty()) {
            Toast.makeText(getActivity(), "Please enter the line for the Over/Under", Toast.LENGTH_LONG).show()
            return
        }
        if (choiceFB.isEmpty() || ((choiceFB != "Over") && (choiceFB != "Under"))) {
            Toast.makeText(getActivity(), "Please enter either Over or Under", Toast.LENGTH_LONG).show()
            return
        }

        val wagerFB = Integer.parseInt(wagerFBString)
        val toWinFB = Integer.parseInt(toWinFBString)

        val ref = FirebaseDatabase.getInstance().getReference("ouBets")
        //get id
        val ouBetId = ref.push().key

        //create instance
        val ouBet = ouBet(
            ouBetId, teamOneOU, teamTwoOU, choiceFB, oddsFB, lineFB, sourceFB, wagerFB,toWinFB,
            didWin = false,
            liveBet = true
        )

        if (ouBetId != null) {
            ref.child(ouBetId).setValue(ouBet).addOnCompleteListener {
                Toast.makeText(getActivity(), "Over/Under Bet Completed", Toast.LENGTH_LONG)
                    .show()
            }
        }


    }
}
