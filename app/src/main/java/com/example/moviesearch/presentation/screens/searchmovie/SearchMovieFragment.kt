package com.example.moviesearch.presentation.screens.searchmovie

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.moviesearch.App
import com.example.moviesearch.R
import com.example.moviesearch.databinding.FragmentSearchMovieBinding
import com.example.moviesearch.presentation.basecomponents.BaseFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchMovieFragment : BaseFragment<FragmentSearchMovieBinding>() {

    @Inject
    lateinit var viewModelFactory: SearchMoviesViewModelFactory
    private val viewModel: SearchMovieViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var bottomSheet: BottomSheetBehavior<View>

    private val adapter = SearchMovieAdapter { id ->
        navigateToMovie(id)
    }

    override fun onAttach(context: Context) {
        (activity?.application as App).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomSheet = BottomSheetBehavior.from(binding.bottomSheet)
        bottomSheet.state = BottomSheetBehavior.STATE_HIDDEN

        initRecyclerView()
        initSearchView()
        initBottomSheet()

        lifecycleScope.launch {
            viewModel.error.collect {
                if (it) {
                    showError()
                }
            }
        }

    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchMovieBinding {
        return FragmentSearchMovieBinding.inflate(inflater, container, false)
    }

    private fun initRecyclerView() {
        binding.rvListMovie.adapter = adapter
        lifecycleScope.launch {
            viewModel.moviesFlow.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    private fun initSearchView() {
        with(binding) {
            searchView.editText.doAfterTextChanged {
                viewModel.getAllMovies(it.toString())

            }
            searchView
                .editText
                .setOnEditorActionListener { v, actionId, event ->
                    searchBar.text = searchView.text
                    searchView.hide()
                    false
                }
        }
    }

    private fun navigateToMovie(id: Int) {
        findNavController().navigate(
            R.id.action_allMoviesFragment_to_movieInfoFragment,
            bundleOf(Pair("ID", id))
        )
    }

    private fun initBottomSheet() {
        binding.filterButton.setOnClickListener {
            bottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
        }

        binding.saveFilterButton.setOnClickListener {
            val country = binding.countryEditText.text.toString().takeIf { it.isNotBlank() }
            val year = binding.yearEditText.text.toString().toIntOrNull()
            val age = binding.ageEditText.text.toString().toIntOrNull()
            val isOlder = binding.ageSwitch.isChecked

            viewModel.filterMovies(country, year, age, isOlder)
            bottomSheet.state = BottomSheetBehavior.STATE_HIDDEN
        }
    }

    private fun showError() {
        Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
    }
}