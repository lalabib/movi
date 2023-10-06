package com.latihan.lalabib.movi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.latihan.lalabib.movi.R
import com.latihan.lalabib.movi.databinding.ItemMovieBinding
import com.latihan.lalabib.movi.domain.model.Movies
import com.latihan.lalabib.movi.utils.SharedObject.IMG_URL

class MovieAdapter(private val onItemClick: (Movies) -> Unit) :
    ListAdapter<Movies, MovieAdapter.MoviViewHolder>(DIFFUTIL) {

    private object DIFFUTIL : DiffUtil.ItemCallback<Movies>() {
        override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviViewHolder(binding, onItemClick)

    }

    override fun onBindViewHolder(holder: MoviViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    class MoviViewHolder(
        private val binding: ItemMovieBinding,
        val onItemClick: (Movies) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movies) {
            binding.apply {
                tvTitle.text = movie.title
                Glide.with(itemView.context)
                    .load(IMG_URL + movie.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_broken_img)
                    )
                    .into(ivPoster)
            }
            itemView.setOnClickListener { onItemClick(movie) }
        }
    }
}