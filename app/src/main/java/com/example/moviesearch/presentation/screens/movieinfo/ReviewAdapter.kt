package com.example.moviesearch.presentation.screens.movieinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.moviesearch.databinding.RvReviewItemBinding
import com.example.moviesearch.domain.models.Review

class ReviewAdapter :
    PagingDataAdapter<Review, ReviewAdapter.ReviewViewHolder>(DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(
            RvReviewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class ReviewViewHolder(private val binding: RvReviewItemBinding) :
        ViewHolder(binding.root) {
        fun bind(review: Review) {
            with(binding) {
                authorName.text = review.author
                titleReview.text = review.title
                dateOfReview.text = review.date
                reviewText.text = review.review
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Review>() {
            override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
                return oldItem == newItem
            }
        }
    }
}
