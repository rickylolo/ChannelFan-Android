package com.example.channelfan.Modelos
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ClassGenero(
    @SerializedName("_id")
    val id: String? = null,

    @Expose(serialize = false)
    @SerializedName("peliculas")
    var peliculas: List<ClassPelicula>? = null,

    @Expose(serialize = true)
    @SerializedName("nombre")
    var nombre: String? = null,

    @Expose(serialize = true)
    @SerializedName("descripcion")
    var descripcion: String? = null
)
