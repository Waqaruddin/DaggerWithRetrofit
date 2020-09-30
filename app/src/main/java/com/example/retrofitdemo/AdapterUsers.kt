package com.example.retrofitdemo

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_adapter_users.view.*

class AdapterUsers (var mContext: Context, var mList:ArrayList<Users>): RecyclerView.Adapter<AdapterUsers.MyViewHolder>(){


    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(user:Users){

           itemView.text_view_name.text = user.firstName
            itemView.text_view_email.text = user._id

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.row_adapter_users, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var user = mList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun setData(l:ArrayList<Users>){
        mList = l
        notifyDataSetChanged()
    }
}