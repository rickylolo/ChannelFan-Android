package com.example.channelfan.models
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ClassReseña(
    @SerializedName("_id")
    val id: String? = null,


    @SerializedName("pelicula")
    var peliculas: List<ClassPelicula>? = null,


    @SerializedName("usuario")
    var usuario: ClassUsuario? = null,


    @SerializedName("titulo")
    var titulo: String? = null,


    @SerializedName("descripcion")
    var descripcion: String? = null,


    @SerializedName("calificacion")
    var calificacion: String? = null,


    @SerializedName("fecha")
    var fecha: String? = null,

    @SerializedName("idPelicula")
    var idPelicula: String? = null,

    @SerializedName("idUsuario")
    var idUsuario: String? = null

)



