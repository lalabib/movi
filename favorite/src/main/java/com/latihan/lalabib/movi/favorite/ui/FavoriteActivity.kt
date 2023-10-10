package com.latihan.lalabib.movi.favorite.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.latihan.lalabib.movi.R
import com.latihan.lalabib.movi.detail.DetailActivity
import com.latihan.lalabib.movi.di.FavoriteDependencies
import com.latihan.lalabib.movi.favorite.adapter.FavMovieAdapter
import com.latihan.lalabib.movi.favorite.databinding.ActivityFavoriteBinding
import com.latihan.lalabib.movi.favorite.di.DaggerFavoriteComponent
import com.latihan.lalabib.movi.favorite.utils.ViewModelFactory
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    @Inject
    lateinit var factory: ViewModelFactory
    private val favViewModel: FavoriteViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(this@FavoriteActivity)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavoriteDependencies::class.java
                )
            )
            .build()
            .inject(this)

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
                putExtra(DetailActivity.EXTRA_DATA, favMovie.id)
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