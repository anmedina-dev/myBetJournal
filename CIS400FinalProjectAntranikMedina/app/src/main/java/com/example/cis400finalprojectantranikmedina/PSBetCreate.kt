package com.example.cis400finalprojectantranikmedina

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_psbet_create.*

class PSBetCreate : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_psbet_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        submitPSbet.setOnClickListener {
            var teamBetOnPS = teamBetOnPSet.text.toString().trim()
            var teamBetAgainstPS = teamBetAgainstPSet.text.toString().trim()
            var oddsPS = oddsPSet.text.toString().trim()
            var wagerPS = wagerPSes.text.toString().trim()
            var toWinPS = toWinPSet.text.toString().trim()
            var sourcePS = sourcePSet.text.toString().trim()
            var spreadPS = spreadPSet.text.toString().trim()
            savePSBet(teamBetOnPS, teamBetAgainstPS, oddsPS, wagerPS, toWinPS, sourcePS, spreadPS)
            val intent = Intent(getActivity(), MainActivity::class.java)
            startActivity(intent)
        }


    }
    private fun savePSBet(teamBetOnPS: String, teamBetAgainstPS: String, oddsPS: String,wagerPS: String, toWinML: String, sourcePS: String, spreadPS: String) {
        val teamBetOnFB = teamBetOnPS
        val teamBetAgainstFB = teamBetAgainstPS
        val oddsFB = oddsPS
        var wagerFBString = wagerPS
        var toWinFBString = toWinML
        val sourceFB = sourcePS
        val spreadFB = spreadPS

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
        if (spreadFB.isEmpty()) {
            Toast.makeText(getActivity(), "Please enter the spread", Toast.LENGTH_LONG).show()
            return
        }
        val wagerFB = Integer.parseInt(wagerFBString)
        val toWinFB = Integer.parseInt(toWinFBString)

        val ref = FirebaseDatabase.getInstance().getReference("psBets")
        //get id
        val psBetId = ref.push().key

        //create instance
        val psBet = psBet(
            psBetId, teamBetOnFB,spreadFB, oddsFB, teamBetAgainstFB, sourceFB, wagerFB, toWinFB,
            didWin = false,
            liveBet = true
        )

        if (psBetId != null) {
            ref.child(psBetId).setValue(psBet).addOnCompleteListener {
                Toast.makeText(getActivity(), "Point Spread Bet Completed", Toast.LENGTH_LONG)
                    .show()
            }
        }


    }
}
