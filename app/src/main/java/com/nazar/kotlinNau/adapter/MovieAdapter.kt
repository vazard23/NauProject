package com.nazar.kotlinNau.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nazar.kotlinNau.R
import com.nazar.kotlinNau.databinding.MovieItemBinding
import com.nazar.kotlinNau.models.Movie

class MovieAdapter() : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){


    private val movies = ArrayList<Movie>()

    fun setData(newList: List<Movie>){
        movies.clear()
        movies.addAll(newList)
        notifyDataSetChanged()

    }

    fun getData() : ArrayList<Movie>{
        return movies
    }


    class MovieViewHolder(private val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root){
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"

        fun bindMovie(movie : Movie){
            binding.movieTitle.text = movie.title
            binding.movieReleaseDate.text = movie.release
            Glide.with(itemView).load(IMAGE_BASE + movie.poster).into(binding.moviePoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }



    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(movies[position])
        val bundle = bundleOf("poster" to movies[position].poster, "description" to movies[position].overview, "vote" to movies[position].vote)
        holder.itemView.setOnClickListener{view-> view.findNavController().navigate(R.id.action_mainFragment_to_movieOverwiew, bundle)}
    }

    override fun getItemCount(): Int = movies.size
}