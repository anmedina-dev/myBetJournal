package com.example.cis400finalprojectantranikmedina

import android.app.PendingIntent.getActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*
import kotlin.contracts.contract

class MyMLRecyclerAdapter(var modelClass: Class<mlBet>,var query: Query):
        FirebaseRecyclerAdapter<mlBet, MyMLRecyclerAdapter.mlBetViewHolder>(
            FirebaseRecyclerOptions.Builder<mlBet>()
                .setQuery(query, modelClass)
                .build()
        ) {
    val items = ArrayList<mlBet>()
    //var myListener: MyItemClickListener? = null
    val TAG = "FBML Adapter"

    private val mDatabase: DatabaseReference = FirebaseDatabase.getInstance().getReference("mlBets")
    private val mlRef = mDatabase.orderByChild("liveBet").equalTo(true)

    var childEventListener = object: ChildEventListener{
        override fun onCancelled(p0: DatabaseError) {
            Log.d(TAG, "child event listener - onCancelled" + p0.toException())
        }

        override fun onChildMoved(p0: DataSnapshot, p1: String?) {
            Log.d(TAG, "child event listener - onChildMoved" + p0.toString())
            val data = p0.getValue<mlBet>(mlBet::class.java)
            val key = p0.key
        }

        override fun onChildChanged(p0: DataSnapshot, p1: String?) {
            Log.d(TAG, "child event listener = onChildChanged" + p0.toString())
            val data = p0.getValue<mlBet>(mlBet::class.java)
            val key = p0.key
            if(data != null && key !=null){
                for((index, mlBet) in items.withIndex()){
                    if(mlBet.id.equals(key)){
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
            val data = p0.getValue<mlBet>(mlBet::class.java)
            val key = p0.key
            if(data != null && key != null) {
                var insertPos = items.size
                for( mlBet in items ){
                    if(mlBet.id.equals(key))
                        return
                }
                items.add(insertPos, data)
                notifyItemInserted(insertPos)
            }
        }

        override fun onChildRemoved(p0: DataSnapshot) {
            Log.d(TAG, "child event listener - onChildRemoved" +p0.toString())
            val data = p0.getValue<mlBet>(mlBet::class.java)
            val key = p0.key
            if(data != null && key != null) {
                var delPos = -1
                for( (index, mlBet) in items.withIndex()){
                    if(mlBet.id.equals(key)){
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
        mlRef.addChildEventListener(childEventListener)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): mlBetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ml_live_card, parent, false)

        return mlBetViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: mlBetViewHolder,
        position: Int,
        mlBet: mlBet
    ) {
        holder.teamBetOn.text = mlBet.teamBetOn
        holder.teamBetAgainst.text = mlBet.teamBetAgainst
        holder.odds.text = mlBet.odds
        holder.source.text = mlBet.source
        holder.toWin.text = mlBet.toWin.toString()
        holder.wager.text = mlBet.wager.toString()

        holder.buttonWonML.setOnClickListener {
            wonLiveBet(position)
        }
        holder.buttonLostML.setOnClickListener {
            lostLiveBet(position)
        }
        //items.add(mlBet)
    }


    inner class mlBetViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var teamBetOn: TextView = view.findViewById(R.id.teamBetOnLiveMLet)
        var teamBetAgainst: TextView = view.findViewById(R.id.teamBetAgainstLiveMLet)
        var wager: TextView = view.findViewById(R.id.wagerBetLiveMLet)
        var source: TextView = view.findViewById(R.id.sourceBetLiveMLet)
        var odds: TextView = view.findViewById(R.id.oddsBetLiveMLet)
        var toWin: TextView = view.findViewById(R.id.toWinBetLiveMLet)

        var buttonWonML: Button = view.findViewById(R.id.wonLiveBetBtnML)
        var buttonLostML: Button = view.findViewById(R.id.lostLiveBetBtnML)


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
        var mlBet = items[position]
        mDatabase.child(mlBet.id.toString()).child("liveBet").setValue(false)
        mDatabase.child(mlBet.id.toString()).child("didWin").setValue(true)
        items[position].didWin = true
        items[position].liveBet = false
        items.removeAt(position)
        notifyItemRemoved(position)
    }

}


