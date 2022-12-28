package com.nazar.kotlinNau.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.nazar.kotlinNau.databinding.FragmentMovieOverwiewBinding

class MovieOverview : Fragment() {

    lateinit var binding: FragmentMovieOverwiewBinding
    private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val poster : String? = arguments?.getString("poster")
        val description : String? = arguments?.getString("description")
        val vote : Double? = arguments?.getDouble("vote")
        binding = FragmentMovieOverwiewBinding.inflate(layoutInflater, container, false)
        binding.textView3.text = description
        binding.voteText.text = vote.toString()
        Glide.with(this).load(IMAGE_BASE + poster).into(binding.imageView2)


        return binding.root
    }
}