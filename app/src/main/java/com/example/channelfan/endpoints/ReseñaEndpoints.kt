package com.example.channelfan.endpoints

import com.example.channelfan.models.ClassReseña
import com.example.channelfan.responses.ReviewsResponse
import retrofit2.Response
import retrofit2.http.*

interface ReseñaWebService {


    @POST("review")
    suspend fun agregarReseña(
        @Body review: ClassReseña
    ): Response<String>

    @GET("pelicula{idPelicula}/review")
    suspend fun obtenerReseñasPelicula(
        @Path("idPelicula") idPelicula: String
    ): Response<ReviewsResponse>

    @GET("user{idUsuario}/review/fav")
    suspend fun obtenerReseñasFavoritas(
        @Path("idUsuario") idUsuario: String
    ): Response<ReviewsResponse>

    @GET("user{idUsuario}/review")
    suspend fun obtenerReseñasUsuario(
        @Path("idUsuario") idUsuario: String
    ): Response<ReviewsResponse>

    @PUT("review{idReview}")
    suspend fun actualizarReseña(
        @Path("idReview") idReseña: String,
        @Body reseña: ClassReseña
    ): Response<String>

    @DELETE("review{idReview}")
    suspend fun borrarReseña(
        @Path("idReview") idReseña: String
    ): Response<String>
}
