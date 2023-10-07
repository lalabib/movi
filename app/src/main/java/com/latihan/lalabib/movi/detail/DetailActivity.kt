package com.latihan.lalabib.movi.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.latihan.lalabib.movi.MyApplication
import com.latihan.lalabib.movi.R
import com.latihan.lalabib.movi.databinding.ActivityDetailBinding
import com.latihan.lalabib.movi.core.domain.model.Movies
import com.latihan.lalabib.movi.core.utils.SharedObject.IMG_URL
import com.latihan.lalabib.movi.core.utils.ViewModelFactory
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    @Inject
    lateinit var factory: ViewModelFactory
    private val detailViewModel: DetailViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
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

            Glide.with(this@DetailActivity)
                .load(IMG_URL + movie.posterPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_broken_img)
                )
                .into(ivPosterImage)

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