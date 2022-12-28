package com.nazar.kotlinNau.repository

import com.nazar.kotlinNau.models.Movie
import com.nazar.kotlinNau.models.MovieResponse
import com.nazar.kotlinNau.services.MovieApiInterface
import com.nazar.kotlinNau.services.MovieApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

     fun getMovieData(callback: (List<Movie>) -> Unit){
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMovieList().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }

        })
    }

}