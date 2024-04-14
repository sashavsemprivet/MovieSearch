package com.example.moviesearch.presentation.screens.searchmovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.moviesearch.databinding.RvMovieItemBinding
import com.example.moviesearch.domain.models.Movie
import com.example.moviesearch.presentation.util.addPlusToEnd
import com.example.moviesearch.presentation.util.endToYear
import com.example.moviesearch.presentation.util.loadAndSetLogo
import com.example.moviesearch.presentation.util.translateType

class SearchMovieAdapter(
    val onOpenMovieInfo: (Int) -> Unit
) : PagingDataAdapter<Movie, SearchMovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            RvMovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class MovieViewHolder(private val binding: RvMovieItemBinding) :
        ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                movieName.text = movie.name
                movieYear.text = movie.year.toString().endToYear()
                movieType.text = movie.type.translateType()
                movieCountry.text = movie.countries.joinToString(", ")
                movieAgeRating.text = movie.ageRating?.toString()?.addPlusToEnd() ?: "0+"
                movie.logoUrl?.let(movieLogo::loadAndSetLogo)

            }
            binding.root.setOnClickListener {
                onOpenMovieInfo(movie.id)
            }

        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}