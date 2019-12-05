package com.example.cis400finalprojectantranikmedina

import android.content.ContentValues.TAG
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_ml_live.*
import kotlinx.android.synthetic.main.ml_live_card.*

var mLquery = FirebaseDatabase.getInstance().getReference("mlBets")
    .orderByChild("liveBet")
    .equalTo(true)
    //.orderByKey().orderByChild("liveBet").equalTo(true)
/*
val mlDB = FirebaseDatabase.getInstance().getReference("mlBets")
val mlBetList:ArrayList<mlBet> = ArrayList()

val childEventListener = object: ChildEventListener {
    override fun onChildAdded(dataSnapshot : DataSnapshot?, previousChildName:String? ) {
//A new message has been added
// onChildAdded() will be called for each node at the first time
        val mlBetClone = dataSnapshot!!.getValue(mlBet::class.java)
        mlBetList.add(mlBetClone!!)
    }

    override fun onChildRemoved(dataSnapshot:DataSnapshot?) {
        Log.e(TAG,"onChildRemoved:” + dataSnapshot!!.key)
//A message has been removed
                Val message = dataSnapshot.getValue(mlBet::class.java)
//do something!!
    }

    override fun onCancelled(p0: DatabaseError) {
        Log.e(TAG, "postMessages:onCancelled"
            ,
            p0!!.toException() )
    }

    override fun onChildMoved(p0: DataSnapshot, p1: String?) {
        Log.e(TAG, "onChildMoved:"+ p0!!.key)
//A message has changed position
        val message = p0.getValue(mlBet::class.java)
    }

    override fun onChildChanged(p0: DataSnapshot, p1: String?) {
        Log.e(TAG, "onChildChanged:" + p0!!.key)
//A message has changed
        val mlBetClone = p0.getValue(mlBet::class.java)
//do something!!!
    }

    override fun onChildAdded(p0: DataSnapshot, p1: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onChildRemoved(p0: DataSnapshot) {
        Log.e(TAG,"onChildRemoved:” + dataSnapshot!!.key)
//A message has been removed
                Val message = p0.getValue(mlBet::class.java)
//do something!!
    }
}

 */


class mlLiveFrag : Fragment() {
    var idx:Int=0

    lateinit var myAdapter2:MyMLRecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        if(savedInstanceState != null) {
            idx = savedInstanceState.getInt("index")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ml_live, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(view.context)

        listViewML.layoutManager = layoutManager

        myAdapter2 = MyMLRecyclerAdapter(mlBet::class.java, mLquery)
        listViewML.adapter = myAdapter2

    }

    override fun onStart() {
        super.onStart()
        myAdapter2.startListening()
    }

    override fun onStop() {
        super.onStop()
        myAdapter2.stopListening()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("index", idx)
    }


    override fun onResume() {
        super.onResume()
    }




}
