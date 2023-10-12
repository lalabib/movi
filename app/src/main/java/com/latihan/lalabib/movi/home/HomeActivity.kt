package com.latihan.lalabib.movi.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.latihan.lalabib.movi.R
import com.latihan.lalabib.movi.core.adapter.MovieAdapter
import com.latihan.lalabib.movi.core.data.Resource
import com.latihan.lalabib.movi.databinding.ActivityHomeBinding
import com.latihan.lalabib.movi.databinding.ContentHomeBinding
import com.latihan.lalabib.movi.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeContentBinding: ContentHomeBinding

    private lateinit var movieAdapter: MovieAdapter
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        homeContentBinding = binding.contentHome

        setupView()
        setupData()
        search()
        moveActivity()
    }

    private fun setupView() {
        supportActionBar?.title = getString(R.string.home_title)
    }

    private fun setupData() {
        movieAdapter = MovieAdapter { movie ->
            Intent(this@HomeActivity, DetailActivity::class.java).apply {
                putExtra(DetailActivity.EXTRA_DATA, movie.id)
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
                        showLoading(true)
                        Toast.makeText(
                            this@HomeActivity,
                            getString(R.string.error_message),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        homeContentBinding.apply {
            rvMovies.layoutManager = GridLayoutManager(this@HomeActivity, 2)
            rvMovies.adapter = movieAdapter
        }
    }

    private fun search() {
        val search = binding.searchView
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                handleSearchQuery(query)
                search.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) {
                    handleSearchQuery("")
                }
                return false
            }
        })
    }

    private fun handleSearchQuery(query: String) {
        homeViewModel.searchMovie(query).observe(this@HomeActivity) { movie ->
            movieAdapter.submitList(movie)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        homeContentBinding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun moveToFavorite() {
        val uri = Uri.parse("movi://favorite")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    private fun moveActivity() {
        binding.icProfile.setOnClickListener { moveToFavorite() }
    }
}