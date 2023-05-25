package com.example.channelfan.Responses

import com.example.channelfan.Modelos.ClassUsuario
import com.google.gson.annotations.SerializedName

data class UsuariosResponse(
    @SerializedName("users") val listaUsuarios: ArrayList<ClassUsuario>
)
