package com.example.channelfan.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.channelfan.Horizontal
import com.example.channelfan.R
import com.example.channelfan.viewHolders.HorizontalViewHolder

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
