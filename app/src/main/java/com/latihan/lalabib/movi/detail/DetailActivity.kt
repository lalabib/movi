package com.latihan.lalabib.movi.detail

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.latihan.lalabib.movi.R
import com.latihan.lalabib.movi.core.domain.model.Movies
import com.latihan.lalabib.movi.core.utils.SharedObject.loadPosterImage
import com.latihan.lalabib.movi.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupData()
    }

    private fun setupView() {
        supportActionBar?.apply {
            title = getString(R.string.detail_title)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupData() {
        val detailMovie = intent.getParcelableExtra<Movies>(EXTRA_DATA)
        if (detailMovie != null) {
            populatedDetailMovie(detailMovie)
        }
    }

    private fun populatedDetailMovie(movie: Movies) {
        binding.apply {
            tvTitle.text = movie.title
            tvVoteAverage.text = movie.voteAverage
            tvReleaseDate.text = movie.releaseDate
            tvOverview.text = movie.overview
            loadPosterImage(ivPosterImage, movie.posterPath)

            var isFavorite = movie.isFavorite
            setFavorite(isFavorite)

            icFavorite.setOnClickListener {
                isFavorite = !isFavorite
                detailViewModel.setFavoriteMovies(movie, isFavorite)
                setFavorite(isFavorite)

                if (isFavorite) {
                    Toast.makeText(
                        this@DetailActivity,
                        getString(R.string.add_favorite),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@DetailActivity,
                        getString(R.string.remove_favorite),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun setFavorite(state: Boolean) {
        binding.icFavorite.isChecked = state
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}