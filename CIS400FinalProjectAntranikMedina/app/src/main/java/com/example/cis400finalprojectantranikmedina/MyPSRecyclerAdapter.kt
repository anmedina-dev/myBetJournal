package com.example.cis400finalprojectantranikmedina

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*

class MyPSRecyclerAdapter (var modelClass: Class<psBet>,var query: Query):
    FirebaseRecyclerAdapter<psBet, MyPSRecyclerAdapter.psBetViewHolder>(
        FirebaseRecyclerOptions.Builder<psBet>()
            .setQuery(query, modelClass)
            .build()
    ) {

    val items = ArrayList<psBet>()
    //var myListener: MyItemClickListener? = null
    val TAG = "FBPS Adapter"


    private val mDatabase: DatabaseReference = FirebaseDatabase.getInstance().getReference("psBets")
    private val psRef = mDatabase.orderByChild("liveBet").equalTo(true)

    var childEventListener = object: ChildEventListener {
        override fun onCancelled(p0: DatabaseError) {
            Log.d(TAG, "child event listener - onCancelled" + p0.toException())
        }

        override fun onChildMoved(p0: DataSnapshot, p1: String?) {
            Log.d(TAG, "child event listener - onChildMoved" + p0.toString())
            val data = p0.getValue<psBet>(psBet::class.java)
            val key = p0.key
        }

        override fun onChildChanged(p0: DataSnapshot, p1: String?) {
            Log.d(TAG, "child event listener = onChildChanged" + p0.toString())
            val data = p0.getValue<psBet>(psBet::class.java)
            val key = p0.key
            if(data != null && key !=null){
                for((index, psBet) in items.withIndex()){
                    if(psBet.id.equals(key)){
                        items.removeAt(index)
                        items.add(index, data)
                        notifyItemChanged(index)
                        break
                    }
                }
            }
        }

        override fun onChildAdded(p0: DataSnapshot, p1: String?) {
            Log.d(TAG, "child event listener - onChildAdded" +p0.toString())
            val data = p0.getValue<psBet>(psBet::class.java)
            val key = p0.key
            if(data != null && key != null) {
                var insertPos = items.size
                for( psBet in items ){
                    if(psBet.id.equals(key))
                        return
                }
                items.add(insertPos, data)
                notifyItemInserted(insertPos)
            }
        }

        override fun onChildRemoved(p0: DataSnapshot) {
            Log.d(TAG, "child event listener - onChildRemoved" +p0.toString())
            val data = p0.getValue<psBet>(psBet::class.java)
            val key = p0.key
            if(data != null && key != null) {
                var delPos = -1
                for( (index, psBet) in items.withIndex()){
                    if(psBet.id.equals(key)){
                        delPos = index
                        break
                    }
                }
                if( delPos != -1 ){
                    items.removeAt(delPos)
                    notifyItemRemoved(delPos)
                }
            }
        }

    }
    init {
        psRef.addChildEventListener(childEventListener)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): psBetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ps_live_card, parent, false)
        return psBetViewHolder(view)
    }

    override fun onBindViewHolder(holder: psBetViewHolder, position: Int, psBet: psBet) {
        holder.teamBetOn.text = psBet.teamBetOn
        holder.teamBetAgainst.text = psBet.teamBetAgainst
        holder.odds.text = psBet.odds
        holder.source.text = psBet.source
        holder.toWin.text = psBet.toWin.toString()
        holder.wager.text = psBet.wager.toString()
        holder.spread.text = psBet.spread

        holder.buttonWonPS.setOnClickListener {
            wonLiveBet(position)
        }
        holder.buttonLostPS.setOnClickListener {
            lostLiveBet(position)
        }

    }


    inner class psBetViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var teamBetOn: TextView = view.findViewById(R.id.teamBetOnLivePSet)
        var teamBetAgainst: TextView = view.findViewById(R.id.teamBetAgainstLivePSet)
        var wager: TextView = view.findViewById(R.id.wagerLivePSes)
        var source: TextView = view.findViewById(R.id.sourceLivePSet)
        var odds: TextView = view.findViewById(R.id.oddsLivePSet)
        var toWin: TextView = view.findViewById(R.id.toWinLivePSet)
        var spread: TextView = view.findViewById(R.id.spreadLivePSet)

        var buttonWonPS: Button = view.findViewById(R.id.wonLiveBetBtnPS)
        var buttonLostPS: Button = view.findViewById(R.id.lostLiveBetBtnPS)
    }

    fun lostLiveBet(position: Int){
        var mlBet = items[position]
        mDatabase.child(mlBet.id.toString()).child("liveBet").setValue(false)
        mDatabase.child(mlBet.id.toString()).child("didWin").setValue(false)
        items[position].didWin = false
        items[position].liveBet = false
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun wonLiveBet(position: Int){
        var psBet = items[position]
        mDatabase.child(psBet.id.toString()).child("liveBet").setValue(false)
        mDatabase.child(psBet.id.toString()).child("didWin").setValue(true)
        items[position].didWin = true
        items[position].liveBet = false
        items.removeAt(position)
        notifyItemRemoved(position)
    }
}