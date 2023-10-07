package com.latihan.lalabib.movi.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.latihan.lalabib.movi.MyApplication
import com.latihan.lalabib.movi.R
import com.latihan.lalabib.movi.core.adapter.FavMovieAdapter
import com.latihan.lalabib.movi.databinding.ActivityFavoriteBinding
import com.latihan.lalabib.movi.detail.DetailActivity
import com.latihan.lalabib.movi.core.utils.ViewModelFactory
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    @Inject
    lateinit var factory: ViewModelFactory
    private val favViewModel: FavoriteViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
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