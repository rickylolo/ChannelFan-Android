package com.example.channelfan.responses

import com.example.channelfan.models.ClassGenero
import com.example.channelfan.models.ClassReseña
import com.google.gson.annotations.SerializedName

data class ReviewsResponse(
    @SerializedName("reviews") val listaReseñas: ArrayList<ClassReseña>
)
