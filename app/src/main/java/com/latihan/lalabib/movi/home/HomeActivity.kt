package com.latihan.lalabib.movi.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.latihan.lalabib.movi.R
import com.latihan.lalabib.movi.core.adapter.MovieAdapter
import com.latihan.lalabib.movi.core.data.Resource
import com.latihan.lalabib.movi.databinding.ActivityHomeBinding
import com.latihan.lalabib.movi.detail.DetailActivity
import com.latihan.lalabib.movi.favorite.FavoriteActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupData()
    }

    private fun setupView() {
        supportActionBar?.title = getString(R.string.home_title)
    }

    private fun setupData() {
        val movieAdapter = MovieAdapter { movie ->
            Intent(this@HomeActivity, DetailActivity::class.java).apply {
                putExtra(DetailActivity.EXTRA_DATA, movie)
                startActivity(this)
            }
        }

        homeViewModel.movie.observe(this@HomeActivity) { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> {
                        showLoading(true)
                    }

                    is Resource.Success -> {
                        showLoading(false)
                        movieAdapter.submitList(movie.data)
                    }

                    is Resource.Error -> {
                        showLoading(false)
                        Toast.makeText(
                            this@HomeActivity,
                            getString(R.string.error_message),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        binding.apply {
            rvMovies.layoutManager = GridLayoutManager(this@HomeActivity, 2)
            rvMovies.adapter = movieAdapter
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favorite -> {
                startActivity(Intent(this@HomeActivity, FavoriteActivity::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}