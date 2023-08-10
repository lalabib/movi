package com.latihan.lalabib.movi.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.latihan.lalabib.movi.R
import com.latihan.lalabib.movi.databinding.ActivityHomeBinding
import com.latihan.lalabib.movi.ui.detail.DetailActivity
import com.latihan.lalabib.movi.utils.ViewModelFactory

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupViewModel()
        setupData()
    }

    private fun setupView() {
        supportActionBar?.title = getString(R.string.home_title)
    }

    private fun setupViewModel() {
        val factory = ViewModelFactory.getInstance()
        homeViewModel = ViewModelProvider(this@HomeActivity, factory)[HomeViewModel::class.java]
    }

    private fun setupData() {
        val movieAdapter = MoviAdapter { movie ->
            Intent(this@HomeActivity, DetailActivity::class.java).apply {
                putExtra(DetailActivity.EXTRA_DATA, movie.id)
                startActivity(this)
            }
        }

        homeViewModel.getMovies().observe(this) {
            movieAdapter.submitList(it.results)
        }

        binding.apply {
            rvMovies.layoutManager = GridLayoutManager(this@HomeActivity, 2)
            rvMovies.adapter = movieAdapter
        }
    }
}