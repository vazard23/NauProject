package com.nazar.kotlinNau.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.nazar.kotlinNau.adapter.MovieAdapter
import com.nazar.kotlinNau.databinding.FragmentMainBinding
import com.nazar.kotlinNau.models.Movie
import com.nazar.kotlinNau.repository.Repository
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding
    lateinit var recyclerView: RecyclerView
    lateinit var movieAdapter: MovieAdapter
    var repository: Repository = Repository()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        recyclerView = binding.rvMoviesList
        movieAdapter = MovieAdapter()
        recyclerView.adapter = movieAdapter
        repository.getMovieData { movies: List<Movie> -> movieAdapter.setData(movies) }
        onRefresh()
        return binding.root
    }

    private fun onRefresh(){
        binding.swipe.setOnRefreshListener {
            swipe.isRefreshing = false
            repository.getMovieData { movies: List<Movie> -> movieAdapter.setData(movies) }
            if (movieAdapter.getData().size == 0){
                Toast.makeText(requireContext(), "No Internet Connection", Toast.LENGTH_LONG).show()
            }
        }
    }

}