package com.hank.moviemania

import android.app.Application
import com.hank.moviemania.api.MovieApiService
import com.hank.moviemania.api.RetrofitHelper
import com.hank.moviemania.db.MovieDatabase
import com.hank.moviemania.repository.MovieRepository


class MyApplication : Application() {

//    lateinit var movieRepository: MovieRepository

    override fun onCreate() {
        super.onCreate()
//        initialize()
    }

    private fun initialize() {
//        val database = MovieDatabase.getDatabase(applicationContext)
//        movieRepository = MovieRepository( database)
    }
}