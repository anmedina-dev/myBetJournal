package com.example.cis400finalprojectantranikmedina

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.FieldPosition

class RvAdapter(val userList: ArrayList<BetTypesList>) : androidx.recyclerview.widget.RecyclerView.Adapter<RvAdapter.ViewHolder>() {
    var lastPosition = -1


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.bet_type_item, p0, false)
        return ViewHolder(v)
    }
    override fun getItemCount(): Int {
        return userList.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val betTypesList: BetTypesList = userList[position]
        holder.title.text = betTypesList.betTitle
        holder.description.text = betTypesList.betDescription
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.betTitle) as TextView
        val description = itemView.findViewById<TextView>(R.id.betDescription) as TextView

    }
}