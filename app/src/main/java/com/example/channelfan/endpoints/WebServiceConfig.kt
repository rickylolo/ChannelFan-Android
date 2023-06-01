package com.example.channelfan.endpoints

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory




object AppConstantes {
    const val BASE_URL ="http://192.168.100.11:3000/api/"
}

//"https://channelfan-server.herokuapp.com/api/"

object RetrofitClient {
    val USER_WEB_SERVICE: WebService by lazy {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        Retrofit.Builder()
            .baseUrl(AppConstantes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(WebService::class.java)
    }

    val MOVIE_WEB_SERVICE: PeliculaWebService by lazy {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        Retrofit.Builder()
            .baseUrl(AppConstantes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(PeliculaWebService::class.java)
    }

    val REVIEW_WEB_SERVICE: ReseñaWebService by lazy {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        Retrofit.Builder()
            .baseUrl(AppConstantes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ReseñaWebService::class.java)
    }

    val GENRE_WEB_SERVICE: GeneroWebService by lazy {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        Retrofit.Builder()
            .baseUrl(AppConstantes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(GeneroWebService::class.java)
    }

    
    
}