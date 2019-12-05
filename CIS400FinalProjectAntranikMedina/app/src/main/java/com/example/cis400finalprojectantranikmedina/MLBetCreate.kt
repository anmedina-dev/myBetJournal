package com.example.cis400finalprojectantranikmedina

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_mlbet_create.*
import kotlinx.android.synthetic.main.fragment_psbet_create.*


class MLBetCreate : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mlbet_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        submitBetML.setOnClickListener {
            var teamBetOnML = teamBetOnMLet.text.toString().trim()
            var teamBetAgainstML = teamBetAgainstMLet.text.toString().trim()
            var oddsML = oddsBetMLet.text.toString().trim()
            var wagerML = wagerBetMLet.text.toString().trim()
            var toWinML = toWinBetMLet.text.toString().trim()
            var sourceML = sourceBetMLet.text.toString().trim()
            saveMLBet(teamBetOnML, teamBetAgainstML, oddsML, wagerML, toWinML, sourceML)
            val intent = Intent(getActivity(), MainActivity::class.java)
            startActivity(intent)
        }


    }

    private fun saveMLBet(teamBetOnML: String, teamBetAgainstML: String, oddsML: String,wagerML: String, toWinML: String, sourceML: String) {
        val teamBetOnFB = teamBetOnML
        val teamBetAgainstFB = teamBetAgainstML
        val oddsFB = oddsML
        var wagerFBString = wagerML
        var toWinFBString = toWinML
        val sourceFB = sourceML

        if (teamBetOnFB.isEmpty()) {
            Toast.makeText(getActivity(), "Please enter a team to bet on", Toast.LENGTH_LONG).show()
            return
        }
        if (teamBetAgainstFB.isEmpty()) {
            Toast.makeText(getActivity(), "Please enter a team to bet against", Toast.LENGTH_LONG).show()
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
        val wagerFB = Integer.parseInt(wagerFBString)
        val toWinFB = Integer.parseInt(toWinFBString)

        val ref = FirebaseDatabase.getInstance().getReference("mlBets")
        //get id
        val mlBetId = ref.push().key

        //create instance
        val mlBet = mlBet(
            mlBetId, teamBetOnFB, oddsFB, teamBetAgainstFB, sourceFB, wagerFB, toWinFB,
            didWin = false,
            liveBet = true
        )

        if (mlBetId != null) {
            ref.child(mlBetId).setValue(mlBet).addOnCompleteListener {
                Toast.makeText(getActivity(), "Money Line Bet Completed", Toast.LENGTH_LONG)
                    .show()
            }
        }


    }

}
