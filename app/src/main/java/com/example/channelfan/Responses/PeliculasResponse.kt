package com.example.channelfan.Responses

import com.example.channelfan.Modelos.ClassPelicula
import com.google.gson.annotations.SerializedName

data class PeliculasResponse(
    @SerializedName("generos") val listaPeliculas: ArrayList<ClassPelicula>
)
