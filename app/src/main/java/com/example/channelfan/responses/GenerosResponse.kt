package com.example.channelfan.responses

import com.example.channelfan.models.ClassGenero
import com.google.gson.annotations.SerializedName

data class GenerosResponse(
    @SerializedName("peliculas") val listaGeneros: ArrayList<ClassGenero>
)
