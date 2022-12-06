package com.hank.moviemania.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitHelper {
    private const val BASE_URL = "https://api.themoviedb.org/3/movie/"

    private val instance : Retrofit? = null


    fun getInstance() : Retrofit {
        return instance ?: Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

//    private val builder by lazy { Retrofit.Builder()
//        .baseUrl(ServerConstants.API_CLOUDFUNCTIONS)
//        .addConverterFactory(GsonConverterFactory.create(gson))
//        .client(httpClient.build())
//        .build() }

    fun <T> buildRetrofitService(serviceType: Class<T>): T {
        return getInstance().create(serviceType)
    }
}