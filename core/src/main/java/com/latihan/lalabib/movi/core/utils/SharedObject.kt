package com.latihan.lalabib.movi.core.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.latihan.lalabib.movi.core.R

object SharedObject {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val IMG_URL = "https://image.tmdb.org/t/p/w500"
    const val passphrase = "lalabib"

    const val hostname = "themoviedb.org"
    const val certificatePinner1 = "sha256/5VLcahb6x4EvvFrCF2TePZulWqrLHS2jCg9Ywv6JHog="
    const val certificatePinner2 = "sha256/vxRon/El5KuI4vx5ey1DgmsYmRY0nDd5Cg4GfJ8S+bg="
    const val certificatePinner3 = "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI="

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

