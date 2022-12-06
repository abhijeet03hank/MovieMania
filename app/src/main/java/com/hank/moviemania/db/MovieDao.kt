package com.hank.moviemania.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hank.moviemania.model.Movie


@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovies(movieList: List<Movie>)

    @Query("SELECT * FROM movie_table")
     fun getMovies() : List<Movie>
}