package com.example.channelfan.endpoints

import com.example.channelfan.responses.UsuariosResponse
import com.example.channelfan.models.ClassUsuario
import retrofit2.Response
import retrofit2.http.*

interface WebService {
    @GET("user")
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


    @POST("user/review/fav")
    suspend fun agregarReseñaFavoritos(
        @Body body: HashMap<String, String>
    ):Response<String>




    @GET("user{idUsuario}/review/fav")
    suspend fun obtenerReseñasFavoritasUsuario(
        @Path("idUsuario") idUsuario: String
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
