package com.example.cis400finalprojectantranikmedina

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_ou_frag_live.*

var ouquery = FirebaseDatabase.getInstance().getReference("ouBets")
    .orderByChild("liveBet")
    .equalTo(true)
//.orderByKey().orderByChild("liveBet").equalTo(true)


class ouFragLive : Fragment() {
    var idx:Int = 0
    lateinit var  myAdapter2: MyOURecyclerAdapter

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
        return inflater.inflate(R.layout.fragment_ou_frag_live, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(view.context)

        listViewOU.layoutManager = layoutManager

        myAdapter2 = MyOURecyclerAdapter(ouBet::class.java, ouquery)
        listViewOU.adapter = myAdapter2
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
