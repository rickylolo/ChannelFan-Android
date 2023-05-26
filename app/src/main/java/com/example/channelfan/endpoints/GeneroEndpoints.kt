package com.example.channelfan.endpoints

import com.example.channelfan.models.ClassGenero
import com.example.channelfan.responses.GenerosResponse
import retrofit2.Response
import retrofit2.http.*



interface GeneroWebService {
    @GET("genero")
    suspend fun obtenerGeneros(): Response<GenerosResponse>

    @GET("genero{idGenero}")
    suspend fun obtenerGenero(
        @Path("idGenero") idGenero: String

    ): Response<ClassGenero>

    @GET("genero{idGenero}/pelicula")
    suspend fun obtenerPeliculasDeUnGenero(
        @Path("idGenero") idGenero: String

    ): Response<ClassGenero>

    @POST("genero")
    suspend fun agregarGenero(
        @Body genero: ClassGenero
    ):Response<String>

    @PUT("genero{idGenero}")
    suspend fun actualizarGenero(
        @Path("idGenero") idGenero: String,
        @Body genero: ClassGenero
    ):Response<String>

    @DELETE("genero{idGenero}")
    suspend fun borrarGenero(
        @Path("idGenero") idGenero: String
    ):Response<String>
}
