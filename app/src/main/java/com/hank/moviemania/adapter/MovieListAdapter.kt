package com.hank.moviemania.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.hank.moviemania.R
import com.hank.moviemania.model.Movie
import kotlinx.android.synthetic.main.row_movie_list_view.view.*

class MovieListAdapter(context: Context,list: ArrayList<Movie> ) : androidx.recyclerview.widget.RecyclerView.Adapter<MovieListAdapter.MyViewHolder>() {
    val TAG = "MovieListAdapter"
    private var list = ArrayList<Movie>()
    private var context: Context

    init {
        this.list = list
        this.context = context
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_movie_list_view, parent, false)
        return MyViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        try {
            val movie = list[position]
            holder.itemView.tvMovieTitle.text = movie.title
            holder.itemView.tvMovieDescription.text = movie.overview

            var full_path = "https://image.tmdb.org/t/p/w500/" + movie.poster_path


            Glide.with(context)
                .load(full_path)
//                .centerCrop()
                .into(holder.itemView.ivMoviePoster);
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }
    }


    inner class MyViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView)

    override fun getItemCount(): Int {
        return list.size
    }
}
