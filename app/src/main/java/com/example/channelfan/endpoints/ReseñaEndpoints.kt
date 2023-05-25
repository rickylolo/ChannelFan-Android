package com.example.channelfan.endpoints

import com.example.channelfan.models.ClassReseña
import com.example.channelfan.responses.ReseñasResponse
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
