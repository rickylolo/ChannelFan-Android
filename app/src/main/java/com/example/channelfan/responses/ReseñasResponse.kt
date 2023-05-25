package com.example.channelfan.responses

import com.example.channelfan.models.ClassGenero
import com.google.gson.annotations.SerializedName

data class ReseñasResponse(
    @SerializedName("reseñas") val listaReseñas: ArrayList<ClassGenero>
)
