package com.example.channelfan.responses

import com.example.channelfan.models.ClassPelicula
import com.google.gson.annotations.SerializedName

data class PeliculasResponse(
    @SerializedName("peliculas") val listaPeliculas: ArrayList<ClassPelicula>
)
