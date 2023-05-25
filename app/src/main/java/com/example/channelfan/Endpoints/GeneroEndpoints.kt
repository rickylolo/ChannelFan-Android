package com.example.channelfan.Endpoints

import com.example.channelfan.Modelos.ClassGenero
import com.example.channelfan.Responses.GenerosResponse
import retrofit2.Response
import retrofit2.http.*



interface GeneroWebService {
    @GET("genero")
    suspend fun obtenerGeneros(): Response<GenerosResponse>

    @GET("genero{idGenero}")
    suspend fun obtenerGenero(
        @Path("idGenero") idGenero: String

    ): Response<ClassGenero>

    @POST("genero")
    suspend fun agregarUsuario(
        @Body genero: ClassGenero
    ):Response<String>

    @PUT("genero{idGenero}")
    suspend fun actualizarUsuario(
        @Path("idGenero") idGenero: String,
        @Body genero: ClassGenero
    ):Response<String>

    @DELETE("genero{idGenero}")
    suspend fun borrarUsuario(
        @Path("idGenero") idGenero: String
    ):Response<String>
}
