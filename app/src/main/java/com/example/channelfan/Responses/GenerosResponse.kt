package com.example.channelfan.Responses

import com.example.channelfan.Modelos.ClassGenero
import com.google.gson.annotations.SerializedName

data class GenerosResponse(
    @SerializedName("peliculas") val listaGeneros: ArrayList<ClassGenero>
)
