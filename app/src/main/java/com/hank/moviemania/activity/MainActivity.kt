package com.hank.moviemania.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hank.moviemania.MyApplication
import com.hank.moviemania.R
import com.hank.moviemania.adapter.MovieListAdapter
import com.hank.moviemania.db.MovieDatabase
import com.hank.moviemania.model.Movie
import com.hank.moviemania.model.MovieList
import com.hank.moviemania.repository.MovieRepository
import com.hank.moviemania.viewmodel.MainViewModel
import com.hank.moviemania.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    lateinit var mainViewModel: MainViewModel
    lateinit var movieRepository: MovieRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnHitReq = findViewById<Button>(R.id.btnHitReq)

        btnHitReq.setOnClickListener {
            if(null!=mainViewModel){
                mainViewModel.getTopRatedMovie()
            }
        }

        btnNext.setOnClickListener {
            if(null!=mainViewModel){
                mainViewModel.updatePageCount(true)
            }
        }
        btnPrevious.setOnClickListener {
            if(null!=mainViewModel){
                mainViewModel.updatePageCount(false)
            }
        }
        try {
//            val repository = (application as MyApplication).movieRepository
            val database = MovieDatabase.getDatabase(applicationContext)
            movieRepository = MovieRepository( database)
            val mainViewModelFactory = MainViewModelFactory(movieRepository,application)
            mainViewModel = ViewModelProvider(this@MainActivity,mainViewModelFactory)[MainViewModel::class.java]
            mainViewModel.moviesLiveList.observe(this, Observer {
                populateMovieList(it)
            })
            mainViewModel.getTopRatedMovie()
        }catch (e:Exception){
            e.stackTrace
            Log.e(TAG,e.toString())
        }

    }

    private fun populateMovieList(movieList: ArrayList<Movie>?){
        movieList?.let {
            rvMoviesList.layoutManager =
                LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            var movieListAdapter = MovieListAdapter(this@MainActivity, movieList)
            rvMoviesList.adapter = movieListAdapter
            tvMovieCount.text = "Total:"+movieList.size.toString()
            tvMoviePageCount.text = "Page:"+mainViewModel.pageCount.toString()

        }
    }



}