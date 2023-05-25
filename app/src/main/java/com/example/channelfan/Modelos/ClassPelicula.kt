package com.example.channelfan.Modelos
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ClassPelicula(
    @SerializedName("_id")
    val id: String? = null,

    @Expose(serialize = true)
    @SerializedName("titulo")
    var titulo: String? = null,

    @Expose(serialize = true)
    @SerializedName("año")
    var año: String? = null,

    @Expose(serialize = true)
    @SerializedName("director")
    var director: String? = null,

    @Expose(serialize = true)
    @SerializedName("generos")
    var generos: List<ClassGenero>? = null,

    @Expose(serialize = true)
    @SerializedName("clasificacion")
    var clasificacion: String? = null,

    @Expose(serialize = true)
    @SerializedName("sinopsis")
    var sinopsis: String? = null,

    @Expose(serialize = true)
    @SerializedName("calificacion")
    var calificacion: String? = null,

    @Expose(serialize = true)
    @SerializedName("imagen")
    var imagen: String? = null,

    @Expose(serialize = true)
    @SerializedName("duracion")
    var duracion: String? = null,

    @Expose(serialize = true)
    @SerializedName("fechaEstreno")
    var fechaEstreno: String? = null
)

