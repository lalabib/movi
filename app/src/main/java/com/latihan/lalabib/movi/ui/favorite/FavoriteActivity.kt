package com.latihan.lalabib.movi.ui.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.latihan.lalabib.movi.R
import com.latihan.lalabib.movi.adapter.FavMovieAdapter
import com.latihan.lalabib.movi.databinding.ActivityFavoriteBinding
import com.latihan.lalabib.movi.ui.detail.DetailActivity
import com.latihan.lalabib.movi.utils.ViewModelFactory

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var favViewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupViewModel()
        setupData()
    }

    private fun setupView() {
        supportActionBar?.apply {
            title = getString(R.string.favorite_title)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupViewModel() {
        val factory = ViewModelFactory.getInstance(this)
        favViewModel = ViewModelProvider(this@FavoriteActivity, factory)[FavoriteViewModel::class.java]
    }

    private fun setupData() {
        val favMovieAdapter = FavMovieAdapter { favMovie ->
            Intent(this@FavoriteActivity, DetailActivity::class.java).apply {
                putExtra(DetailActivity.EXTRA_DATA, favMovie)
                startActivity(this)
            }

        }

        favViewModel.favoriteMovie.observe(this@FavoriteActivity) {
            favMovieAdapter.submitList(it)
        }

        binding.apply {
            rvFavMovies.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            rvFavMovies.adapter = favMovieAdapter
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}