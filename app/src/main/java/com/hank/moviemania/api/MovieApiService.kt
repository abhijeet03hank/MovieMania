package com.hank.moviemania.api

import com.hank.moviemania.model.MovieList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("top_rated")
     fun getTopRatedMovies(@Query("api_key") api_key : String,@Query("language") language: String ,@Query("page") page: Int) : Call<MovieList>

}