package com.latihan.lalabib.movi.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.latihan.lalabib.movi.R
import com.latihan.lalabib.movi.data.remote.response.DetailMovieResponse
import com.latihan.lalabib.movi.databinding.ActivityDetailBinding
import com.latihan.lalabib.movi.utils.SharedObject.IMG_URL
import com.latihan.lalabib.movi.utils.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupViewModel()
        setupData()
    }

    private fun setupView() {
        supportActionBar?.apply {
            title = getString(R.string.detail_title)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupViewModel() {
        val factory = ViewModelFactory.getInstance()
        detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
    }

    private fun setupData() {
        val extras = intent.extras
        if (extras != null) {
            val id = extras.getString(EXTRA_DATA)
            if (id != null) {
                detailViewModel.setMoviesData(id)
                detailViewModel.detailMovie.observe(this) {detailMovie ->
                    populatedDetailMovie(detailMovie)
                }
            }
        }
    }

    private fun populatedDetailMovie(movie: DetailMovieResponse) {
        binding.apply {
            tvTitle.text = movie.title
            tvVoteAverage.text = movie.voteAverage
            tvReleaseDate.text = movie.releaseDate
            tvOverview.text = movie.overview

            Glide.with(this@DetailActivity)
                .load(IMG_URL + movie.posterPath)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_broken_img))
                .into(ivPosterImage)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}