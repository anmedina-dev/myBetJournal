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

class MyOURecyclerAdapter (var modelClass: Class<ouBet>,var query: Query):
    FirebaseRecyclerAdapter<ouBet, MyOURecyclerAdapter.ouBetViewHolder>(
        FirebaseRecyclerOptions.Builder<ouBet>()
            .setQuery(query, modelClass)
            .build()
    ) {

    val items = ArrayList<ouBet>()
    //var myListener: MyItemClickListener? = null
    val TAG = "FBOU Adapter"

    private val mDatabase: DatabaseReference = FirebaseDatabase.getInstance().getReference("ouBets")
    private val ouRef = mDatabase.orderByChild("liveBet").equalTo(true)

    var childEventListener = object: ChildEventListener {
        override fun onCancelled(p0: DatabaseError) {
            Log.d(TAG, "child event listener - onCancelled" + p0.toException())
        }

        override fun onChildMoved(p0: DataSnapshot, p1: String?) {
            Log.d(TAG, "child event listener - onChildMoved" + p0.toString())
            val data = p0.getValue<ouBet>(ouBet::class.java)
            val key = p0.key
        }

        override fun onChildChanged(p0: DataSnapshot, p1: String?) {
            Log.d(TAG, "child event listener = onChildChanged" + p0.toString())
            val data = p0.getValue<ouBet>(ouBet::class.java)
            val key = p0.key
            if(data != null && key !=null){
                for((index, ouBet) in items.withIndex()){
                    if(ouBet.id.equals(key)){
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
            val data = p0.getValue<ouBet>(ouBet::class.java)
            val key = p0.key
            if(data != null && key != null) {
                var insertPos = items.size
                for( ouBet in items ){
                    if(ouBet.id.equals(key))
                        return
                }
                items.add(insertPos, data)
                notifyItemInserted(insertPos)
            }
        }

        override fun onChildRemoved(p0: DataSnapshot) {
            Log.d(TAG, "child event listener - onChildRemoved" +p0.toString())
            val data = p0.getValue<ouBet>(ouBet::class.java)
            val key = p0.key
            if(data != null && key != null) {
                var delPos = -1
                for( (index, ouBet) in items.withIndex()){
                    if(ouBet.id.equals(key)){
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
        ouRef.addChildEventListener(childEventListener)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ouBetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ou_live_card, parent, false)
        return ouBetViewHolder(view)
    }

    override fun onBindViewHolder(holder: ouBetViewHolder, position: Int, ouBet: ouBet) {
        holder.teamOne.text = ouBet.teamOne
        holder.teamTwo.text = ouBet.teamTwo
        holder.choice.text = ouBet.overUnder
        holder.source.text = ouBet.source
        holder.toWin.text = ouBet.toWin.toString()
        holder.wager.text = ouBet.wager.toString()
        holder.line.text = ouBet.line
        holder.odds.text = ouBet.odds

        holder.buttonWonOU.setOnClickListener {
            wonLiveBet(position)
        }
        holder.buttonLostOU.setOnClickListener {
            lostLiveBet(position)
        }


    }

    inner class ouBetViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var teamOne: TextView = view.findViewById(R.id.teamOneLiveOUet)
        var teamTwo: TextView = view.findViewById(R.id.teamTwoLiveOUet)
        var choice: TextView = view.findViewById(R.id.choiceLiveOUet)
        var wager: TextView = view.findViewById(R.id.wagerBetLiveOUet)
        var source: TextView = view.findViewById(R.id.sourceBetLiveOUet)
        var odds: TextView = view.findViewById(R.id.oddsBetLiveOUet)
        var toWin: TextView = view.findViewById(R.id.toWinLiveOUet)
        var line: TextView = view.findViewById(R.id.lineLiveOUet)

        var buttonWonOU: Button = view.findViewById(R.id.wonLiveBetBtnOU)
        var buttonLostOU: Button = view.findViewById(R.id.lostLiveBetBtnOU)


    }

    fun lostLiveBet(position: Int){
        var ouBet = items[position]
        mDatabase.child(ouBet.id.toString()).child("liveBet").setValue(false)
        mDatabase.child(ouBet.id.toString()).child("didWin").setValue(false)
        items[position].didWin = false
        items[position].liveBet = false
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun wonLiveBet(position: Int){
        var ouBet = items[position]
        mDatabase.child(ouBet.id.toString()).child("liveBet").setValue(false)
        mDatabase.child(ouBet.id.toString()).child("didWin").setValue(true)
        items[position].didWin = true
        items[position].liveBet = false
        items.removeAt(position)
        notifyItemRemoved(position)
    }


}