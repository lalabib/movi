package com.latihan.lalabib.movi.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.latihan.lalabib.movi.R
import com.latihan.lalabib.movi.data.local.MoviesEntity
import com.latihan.lalabib.movi.databinding.ItemMovieBinding
import com.latihan.lalabib.movi.utils.SharedObject.IMG_URL

class MoviAdapter(private val onItemClick: (MoviesEntity) -> Unit) : ListAdapter<MoviesEntity, MoviAdapter.MoviViewHolder>(DIFFUTIL) {

    private object DIFFUTIL : DiffUtil.ItemCallback<MoviesEntity>() {
        override fun areItemsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviViewHolder {
        val binding =  ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviViewHolder(binding, onItemClick)

    }

    override fun onBindViewHolder(holder: MoviViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    class MoviViewHolder(private val binding: ItemMovieBinding, val onItemClick: (MoviesEntity) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MoviesEntity) {
            binding.apply {
                tvTitle.text = movie.title
                Glide.with(itemView.context)
                    .load(IMG_URL + movie.poster_path)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_broken_img))
                    .into(ivPoster)
            }
            itemView.setOnClickListener { onItemClick(movie) }
        }
    }
}