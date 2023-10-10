package com.latihan.lalabib.movi.core.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.latihan.lalabib.movi.core.R

object SharedObject {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val IMG_URL = "https://image.tmdb.org/t/p/w500"

    fun loadPosterImage(imageView: ImageView, posterPath: String?) {
        val imageUrl = IMG_URL + (posterPath ?: "")
        Glide.with(imageView.context)
            .load(imageUrl)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_broken_img)
            )
            .into(imageView)
    }
}

