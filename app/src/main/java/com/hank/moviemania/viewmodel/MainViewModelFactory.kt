package com.hank.moviemania.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hank.moviemania.repository.MovieRepository

//class MainViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return MainViewModel(repository) as T
//    }
//}

class MainViewModelFactory (private val repository: MovieRepository, private val application: Application): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository, application) as T
    }
}