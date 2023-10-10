package com.latihan.lalabib.movi.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.latihan.lalabib.movi.core.domain.model.Movies
import com.latihan.lalabib.movi.core.utils.SharedObject.loadPosterImage
import com.latihan.lalabib.movi.favorite.databinding.ItemFavMovieBinding

class FavMovieAdapter(private val onItemClick: (Movies) -> Unit) :
    ListAdapter<Movies, FavMovieAdapter.FavMovieViewHolder>(DIFFUTIL) {

    object DIFFUTIL : DiffUtil.ItemCallback<Movies>() {
        override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavMovieViewHolder {
        val binding =
            ItemFavMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavMovieViewHolder(
            binding,
            onItemClick
        )
    }

    override fun onBindViewHolder(holder: FavMovieViewHolder, position: Int) {
        val favMovie = getItem(position)
        if (favMovie != null) {
            holder.bind(favMovie)
        }
    }

    class FavMovieViewHolder(
        private val binding: ItemFavMovieBinding,
        val onItemClick: (Movies) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(favMovie: Movies) {
            binding.apply {
                tvTitle.text = favMovie.title
                tvOverview.text = favMovie.overview
                loadPosterImage(ivPoster, favMovie.posterPath)
            }
            itemView.setOnClickListener { onItemClick(favMovie) }
        }
    }
}