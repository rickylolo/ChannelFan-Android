package com.example.channelfan.responses

import com.example.channelfan.models.ClassUsuario
import com.google.gson.annotations.SerializedName

data class UsuariosResponse(
    @SerializedName("users") val listaUsuarios: ArrayList<ClassUsuario>
)
