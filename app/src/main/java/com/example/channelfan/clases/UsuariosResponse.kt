package com.example.channelfan.clases

import com.example.channelfan.dataclass.Usuario
import com.google.gson.annotations.SerializedName

data class UsuariosResponse(
    @SerializedName("users") val listaUsuarios: ArrayList<Usuario>
)
