package com.example.channelfan.Endpoints

import com.example.channelfan.Responses.UsuariosResponse
import com.example.channelfan.Modelos.ClassUsuario
import retrofit2.Response
import retrofit2.http.*

interface UserWebService {
    @GET("genero")
    suspend fun obtenerUsuarios(): Response<UsuariosResponse>

    @GET("user{idUsuario}")
    suspend fun obtenerUsuario(
        @Path("idUsuario") idUsuario: String

    ): Response<ClassUsuario>

    @POST("user/login")
    suspend fun iniciarSesion(
        @Body usuario: ClassUsuario
    ):Response<String>

    @POST("user")
    suspend fun agregarUsuario(
        @Body usuario: ClassUsuario
    ):Response<String>

    @PUT("user{idUsuario}")
    suspend fun actualizarUsuario(
        @Path("idUsuario") idUsuario: String,
        @Body usuario: ClassUsuario
    ):Response<String>

    @DELETE("user{idUsuario}")
    suspend fun borrarUsuario(
        @Path("idUsuario") idUsuario: String
    ):Response<String>
}
