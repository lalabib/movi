package com.latihan.lalabib.movi.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.latihan.lalabib.movi.R
import com.latihan.lalabib.movi.core.adapter.FavMovieAdapter
import com.latihan.lalabib.movi.databinding.ActivityFavoriteBinding
import com.latihan.lalabib.movi.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private val favViewModel: FavoriteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupData()
    }

    private fun setupView() {
        supportActionBar?.apply {
            title = getString(R.string.favorite_title)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupData() {
        val favMovieAdapter = FavMovieAdapter { favMovie ->
            Intent(this@FavoriteActivity, DetailActivity::class.java).apply {
                putExtra(DetailActivity.EXTRA_DATA, favMovie)
                startActivity(this)
            }
        }

        favViewModel.favoriteMovie.observe(this@FavoriteActivity) { listFavMovie ->
            if (!listFavMovie.isNullOrEmpty()) {
                isNotEmpty()
                favMovieAdapter.submitList(listFavMovie)
            } else {
                isEmpty()
            }
        }

        binding.apply {
            rvFavMovies.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            rvFavMovies.adapter = favMovieAdapter
        }
    }

    private fun isEmpty() {
        binding.apply {
            binding.rvFavMovies.visibility = View.GONE
            binding.ivEmpty.visibility = View.VISIBLE
            binding.tvEmpty.visibility = View.VISIBLE
        }
    }

    private fun isNotEmpty() {
        binding.apply {
            binding.rvFavMovies.visibility = View.VISIBLE
            binding.ivEmpty.visibility = View.GONE
            binding.tvEmpty.visibility = View.GONE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}