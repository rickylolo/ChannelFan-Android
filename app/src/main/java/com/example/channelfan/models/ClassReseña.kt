package com.example.channelfan.models
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ClassReseña(
    @SerializedName("_id")
    val id: String? = null,

    @Expose(serialize = true)
    @SerializedName("pelicula")
    var peliculas: List<ClassPelicula>? = null,

    @Expose(serialize = true)
    @SerializedName("usuario")
    var usuario: ClassUsuario? = null,

    @Expose(serialize = true)
    @SerializedName("titulo")
    var titulo: String? = null,

    @Expose(serialize = true)
    @SerializedName("descripcion")
    var descripcion: String? = null,

    @Expose(serialize = true)
    @SerializedName("calificacion")
    var calificacion: String? = null,

    @Expose(serialize = true)
    @SerializedName("fecha")
    var fecha: String? = null
)
