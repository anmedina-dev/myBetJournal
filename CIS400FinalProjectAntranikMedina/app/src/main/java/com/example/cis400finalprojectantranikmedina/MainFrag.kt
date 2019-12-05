package com.example.cis400finalprojectantranikmedina

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cis400finalprojectantranikmedina.R
import kotlinx.android.synthetic.main.activity_create_bet.*
import kotlinx.android.synthetic.main.fragment_main.*


class MainFrag : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuLearnBetBtn.setOnClickListener {
            val intent = Intent(getActivity(), LearnBetting::class.java)
            startActivity(intent)
        }
        menuBettingHistBtn.setOnClickListener {
            val intent = Intent(getActivity(), BettingHistory::class.java)
            startActivity(intent)
        }
        menuCreateBetBtn.setOnClickListener {
            val intent = Intent(getActivity(), CreateBet::class.java)
            startActivity(intent)
        }
        menuLiveBetsBtn.setOnClickListener {
            val intent = Intent(getActivity(), LiveBets::class.java)
            startActivity(intent)
        }
    }

}
