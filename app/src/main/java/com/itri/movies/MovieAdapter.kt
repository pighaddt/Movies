package com.itri.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation
import kotlinx.android.synthetic.main.movie_row.view.*

class MovieAdapter(var movies : List<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val image = itemView.movie_image
        val titile = itemView.movie_titile
        val pop = itemView.movie_pop
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_row, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        movies?.run {
            val movie = movies.get(position)
            holder.titile.text = movie.title
            holder.pop.text = "popularity : " + movie.popularity.toString()
            holder.image.load("https://image.tmdb.org/t/p/w500${movie.poster_path}"){
                placeholder(R.drawable.picture)
                transformations(CircleCropTransformation())
//                transformations(BlurTransformation(holder.image.context))
            }
        }
    }

    override fun getItemCount(): Int {
        return movies.size ?: 0
    }
}