package com.latihan.lalabib.movi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.latihan.lalabib.movi.R
import com.latihan.lalabib.movi.data.local.entity.MoviesEntity
import com.latihan.lalabib.movi.databinding.ItemFavMovieBinding
import com.latihan.lalabib.movi.utils.SharedObject.IMG_URL

class FavMovieAdapter :
    ListAdapter<MoviesEntity, FavMovieAdapter.FavMovieViewHolder>(DIFFUTIL) {

    object DIFFUTIL : DiffUtil.ItemCallback<MoviesEntity>() {
        override fun areItemsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavMovieViewHolder {
        val binding = ItemFavMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavMovieViewHolder, position: Int) {
        val favMovie = getItem(position)
        if (favMovie != null) {
            holder.bind(favMovie)
        }
    }

    class FavMovieViewHolder(private val binding: ItemFavMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(favMovie: MoviesEntity) {
            binding.apply {
                tvTitle.text = favMovie.title
                tvOverview.text = favMovie.overview
                Glide.with(itemView.context)
                    .load(IMG_URL + favMovie.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_broken_img)
                    )
                    .into(ivPoster)
            }
        }
    }
}