package com.example.cis400finalprojectantranikmedina

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_ouhistory.*


class OUHistoryFrag : Fragment() {
    var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    var myReference: DatabaseReference = database.getReference()
    var profit: Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ouhistory, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myReference.child("ouBets").orderByChild("liveBet").equalTo(false)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) { // get total available quest
                    val size = dataSnapshot.childrenCount.toInt()
                    OUHistoryBetAmount.text = size.toString()
                }

            })
        myReference.child("ouBets")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) { // get total available quest
                    for (data in dataSnapshot.children) {
                        if (data.child("didWin").getValue() == true && data.child("liveBet").getValue() == false)
                            profit = profit + data.child("toWin").getValue(Long::class.java)!!
                        if (data.child("didWin").getValue() == false && data.child("liveBet").getValue() == false)
                            profit = profit - data.child("wager").getValue(Long::class.java)!!
                    }
                    OUHistoryProfit.text = profit.toString()

                }

            })
    }
}
