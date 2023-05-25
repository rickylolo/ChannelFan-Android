package com.example.channelfan.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.channelfan.Horizontal
import com.example.channelfan.R

    class HorizontalAdapter(private val HorizontalList:List<Horizontal>): RecyclerView.Adapter<HorizontalViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return HorizontalViewHolder(layoutInflater.inflate(R.layout.rv_item, parent, false))
        }

        override fun onBindViewHolder(holder: HorizontalViewHolder, position: Int) {
            val item = HorizontalList[position]
            holder.render(item)
        }

        override fun getItemCount(): Int = HorizontalList.size
    }
