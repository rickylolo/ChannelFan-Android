package com.example.channelfan.viewHolders

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.channelfan.R
import com.example.channelfan.models.ClassPelicula

class BannerViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val BannerPhoto = view.findViewById<ImageView>(R.id.ivBanner)


        fun render(BannerModel : ClassPelicula){
            Glide.with(BannerPhoto.context).load(BannerModel.imagen).into(BannerPhoto)
        }
    }