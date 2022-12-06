package com.hank.moviemania.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.hank.moviemania.model.Movie
import com.hank.moviemania.model.MovieList
import com.hank.moviemania.repository.MovieRepository
import com.hank.moviemania.utils.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel(private val repository: MovieRepository,application: Application) : AndroidViewModel(application) {

     val moviesLiveList = MutableLiveData<ArrayList<Movie>?>()
    var pageCount : Int = 1


//    init {
//        viewModelScope.launch(Dispatchers.IO){
//            repository.getMovies(1)
//        }
//    }

//    val movieList : LiveData<MovieList>
//    get() = repository.movies

    fun getTopRatedMovie() {
        viewModelScope.launch(Dispatchers.IO)  {
            if(NetworkUtils.isInternetAvailable(getApplication())) {
                repository.getMovies(pageCount)?.let {
                    if (it?.moviesList?.isNotEmpty()) {
                        moviesLiveList.postValue(it.moviesList as ArrayList<Movie>)
                        repository.addOrUpdateMoviesToDb(it.moviesList)
                    }
                }
            }else{
                repository.getMoviesDataFromDb()?.let {
                    if (it?.moviesList?.isNotEmpty()) {
                        moviesLiveList.postValue(it.moviesList as ArrayList<Movie>)
                    }
                }
            }
        }
    }

    fun updatePageCount(isNext:Boolean){
        if(isNext){
            if(pageCount<529 && pageCount>0) {
                pageCount++
                getTopRatedMovie()
            }
        }else{
            if( pageCount>1) {
                pageCount--
                getTopRatedMovie()
            }
        }

    }
}