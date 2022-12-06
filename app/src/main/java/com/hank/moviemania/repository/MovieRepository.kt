package com.hank.moviemania.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hank.moviemania.api.CustomRetrofitCallback
import com.hank.moviemania.api.MovieApiService
import com.hank.moviemania.api.RetrofitHelper
import com.hank.moviemania.db.MovieDatabase
import com.hank.moviemania.model.Movie
import com.hank.moviemania.model.MovieList
import com.hank.moviemania.utils.Constants
import com.hank.moviemania.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MovieRepository(
    private val movieDatabase: MovieDatabase) {
    private val TAG ="MovieRepository"

    suspend fun getMovies(page: Int):MovieList? = suspendCoroutine{
        try{
//            if(NetworkUtils.isInternetAvailable(applicationContext)){
                var movieService = RetrofitHelper.buildRetrofitService(MovieApiService::class.java)
                val requestCall = movieService.getTopRatedMovies(Constants.API_KEY,"en-US",page)
                Log.e(TAG,requestCall.request().toString())
                requestCall.enqueue(object :CustomRetrofitCallback<MovieList>{
                    override fun onFailure(call: Call<MovieList>, t: Throwable) {
                        Log.e(TAG,"Failed to get list", t)
                        it.resume(null)
                    }

                    override fun onResponse(call: Call<MovieList>?, response: Response<MovieList>?) {
                        try{
                            if (response?.isSuccessful == true) {
                                super.onResponse(call, response)
                                it.resume(response.body())
                            }else{
                                it.resume(null)
                            }
                        } catch (e: Exception) {
                            Log.e(TAG, e.toString())
                            it.resume(null)
                        }
                    }
                })
        } catch (e: Exception) {
            Log.e(TAG, e.toString())

        }
    }

    suspend fun addOrUpdateMoviesToDb(movieList: List<Movie> ){
        try{
            movieDatabase.movieDao().addMovies(movieList)
        } catch (e: Exception) {
            Log.e(TAG, e.toString())

        }
    }

     suspend fun getMoviesDataFromDb(): MovieList?{
         try{
            val movieList = movieDatabase.movieDao().getMovies()
            return if(null!=movieList){
                MovieList(1,movieList,1,1)
            }else{
                null
            }
         } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }
         return null
    }

}