package com.example.moviesearch.presentation.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.moviesearch.R

fun ImageView.loadAndSetLogo(url: String) {
    Glide
        .with(this.rootView)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .override(250, 250)
        .placeholder(R.drawable.logo_movie_not_found)
        .centerCrop()
        .into(this)
}

fun ImageView.loadAndSetMainImage(url: String) {
    Glide
        .with(this.rootView)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.drawable.stub_movie_bid_logo)
        .centerCrop()
        .into(this)
}
