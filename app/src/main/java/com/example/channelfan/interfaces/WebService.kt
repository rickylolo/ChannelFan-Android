package com.example.channelfan.interfaces

import com.example.channelfan.clases.UsuariosResponse
import com.example.channelfan.dataclass.Usuario
import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

object AppConstantes {
    const val BASE_URL ="http://192.168.100.11:3000/api/"
}

interface WebService {
    @GET("user")
    suspend fun obtenerUsuarios(): Response<UsuariosResponse>

    @GET("user{idUsuario}")
    suspend fun obtenerUsuarios(
        @Path("idUsuario") idUsuario: String

    ): Response<Usuario>

    @POST("user")
    suspend fun agregarUsuario(
        @Body usuario: Usuario
    ):Response<String>

    @PUT("user{idUsuario}")
    suspend fun actualizarUsuario(
        @Path("idUsuario") idUsuario: String,
        @Body usuario: Usuario
    ):Response<String>

    @DELETE("user{idUsuario}")
    suspend fun borrarUsuario(
        @Path("idUsuario") idUsuario: String
    ):Response<String>
}

object RetrofitClient {
    val webService: WebService by lazy {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        Retrofit.Builder()
            .baseUrl(AppConstantes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(WebService::class.java)
    }
}