package com.example.week10.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.week10.R
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_recycler.view.*

class userAdapter (private val users:MutableList<user> = mutableListOf(), private val listener: clickAdapter):RecyclerView.Adapter<userAdapter.userViewHolder>(){

    inner class userViewHolder(i : View):RecyclerView.ViewHolder(i){
        val tvId : TextView = i.findViewById(R.id.tvId)
        val tvNama : TextView = i.findViewById(R.id.tvNama)
        val tvEmail : TextView = i.findViewById(R.id.tvEmail)

        fun bindModel(u:user){
            tvId.text = u.getId().toString()
            tvNama.text = u.getNama()
            tvEmail.text = u.getEmail()
        }
    }

    fun setUser(data: List<user>){
        users.clear()
        users.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): userViewHolder {
        return userViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_recycler,parent,false))
    }

    override fun onBindViewHolder(holder: userViewHolder, position: Int) {
        holder.bindModel(users[position])
        holder.itemView.setOnClickListener {
            listener.onClick(users[position])
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    interface clickAdapter{
        fun onClick(user: user)
    }

}