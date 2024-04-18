package com.ansari.demoproj2



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.softaai.beauty_android.data.HeadersData

class MyAdapter(var headerList: ArrayList<HeadersData>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var headerName = itemView.findViewById<TextView>(R.id.textViewHeadings)

        fun myBindData(heading : HeadersData){
            headerName.text = heading.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var myView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return  MyViewHolder(myView)
    }

    override fun getItemCount(): Int {
        return headerList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
         holder.myBindData(headerList[position])
    }
}