package com.example.moviesearch.presentation.screens.movieinfo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.moviesearch.App
import com.example.moviesearch.databinding.FragmentMovieInfoBinding
import com.example.moviesearch.presentation.basecomponents.BaseFragment
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieInfoFragment : BaseFragment<FragmentMovieInfoBinding>() {

    @Inject
    lateinit var viewModelFactory: MovieInfoViewModelFactory
    private val viewModel: MovieInfoViewModel by viewModels {
        viewModelFactory
    }

    override fun onAttach(context: Context) {
        (activity?.application as App).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMovieInfoBinding {
        return FragmentMovieInfoBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getInt("ID")
        id?.let { viewModel.getMovieInfo(it) }


        lifecycleScope.launch {
            viewModel.moviesInfo.collect { uiState ->
                when (uiState) {
                    is UiState.Error -> showError(uiState.errorMessage)
                    is UiState.Success -> {
                        with(binding) {
                            movieName.text = uiState.data.name
                            movieDescription.text = uiState.data.description
                        }
                    }
                }
            }
        }
    }

    private fun showError(errorMessage: String) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }
}