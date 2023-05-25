package com.example.channelfan.Adapters

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.channelfan.Banner
import com.example.channelfan.R

class BannerViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val BannerPhoto = view.findViewById<ImageView>(R.id.ivBanner)


        fun render(BannerModel : Banner){
            Glide.with(BannerPhoto.context).load(BannerModel.photo).into(BannerPhoto)
        }
    }