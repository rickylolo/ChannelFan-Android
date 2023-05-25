package com.example.channelfan.Endpoints

import com.example.channelfan.Modelos.ClassReseña
import com.example.channelfan.Responses.GenerosResponse
import com.example.channelfan.Responses.ReseñasResponse
import retrofit2.Response
import retrofit2.http.*

interface ReseñaWebService {
    @GET("review")
    suspend fun obtenerReseñas(): Response<ReseñasResponse>

    @GET("review/{idReseña}")
    suspend fun obtenerReseña(
        @Path("idReseña") idReseña: String
    ): Response<ClassReseña>

    @POST("review")
    suspend fun agregarReseña(
        @Body reseña: ClassReseña
    ): Response<String>

    @PUT("review/{idReseña}")
    suspend fun actualizarReseña(
        @Path("idReseña") idReseña: String,
        @Body reseña: ClassReseña
    ): Response<String>

    @DELETE("review/{idReseña}")
    suspend fun borrarReseña(
        @Path("idReseña") idReseña: String
    ): Response<String>
}
