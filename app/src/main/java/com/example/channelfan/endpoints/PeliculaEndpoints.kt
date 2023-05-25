package com.example.channelfan.endpoints

import com.example.channelfan.models.ClassPelicula
import com.example.channelfan.responses.PeliculasResponse
import retrofit2.Response
import retrofit2.http.*

interface PeliculaWebService {
    @GET("pelicula")
    suspend fun obtenerPeliculas(): Response<PeliculasResponse>

    @GET("pelicula{idPelicula}")
    suspend fun obtenerPelicula(
        @Path("idPelicula") idPelicula: String
    ): Response<ClassPelicula>

    @POST("pelicula")
    suspend fun agregarPelicula(
        @Body pelicula: ClassPelicula
    ): Response<String>

    @PUT("pelicula{idPelicula}")
    suspend fun actualizarPelicula(
        @Path("idPelicula") idPelicula: String,
        @Body pelicula: ClassPelicula
    ): Response<String>

    @DELETE("pelicula{idPelicula}")
    suspend fun borrarPelicula(
        @Path("idPelicula") idPelicula: String
    ): Response<String>
}
