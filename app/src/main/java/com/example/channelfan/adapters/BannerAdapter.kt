package com.example.channelfan.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.channelfan.R
import com.example.channelfan.models.ClassPelicula
import com.example.channelfan.viewHolders.BannerViewHolder

class BannerAdapter(private val BannerList:List<ClassPelicula>): RecyclerView.Adapter<BannerViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return BannerViewHolder(layoutInflater.inflate(R.layout.item_uno, parent, false))
        }

        override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
            val item = BannerList[position]
            holder.render(item)
        }

        override fun getItemCount(): Int = BannerList.size
    }