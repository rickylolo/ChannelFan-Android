package com.example.channelfan.Endpoints

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory




object AppConstantes {
    const val BASE_URL ="http://192.168.100.11:3000/api/"
}

object RetrofitClient {
    val USER_WEB_SERVICE: UserWebService by lazy {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        Retrofit.Builder()
            .baseUrl(AppConstantes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(UserWebService::class.java)
    }
}