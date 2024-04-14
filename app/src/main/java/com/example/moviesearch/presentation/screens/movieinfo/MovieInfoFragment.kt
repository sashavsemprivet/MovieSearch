package com.example.moviesearch.presentation.screens.movieinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.moviesearch.databinding.FragmentMovieInfoBinding
import kotlinx.coroutines.launch

class MovieInfoFragment : Fragment() {

    private var _binding: FragmentMovieInfoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getInt("ID")
        id?.let { viewModel.getMovieInfo(it) }


        lifecycleScope.launch {
            viewModel.moviesInfo.collect { movie ->

                with(binding) {
                    movieName.text = movie.name
                    movieDescription.text = movie.description
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}