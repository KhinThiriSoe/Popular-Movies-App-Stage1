package com.khinthirisoe.popularmoviesappstage1.ui.detail

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.khinthirisoe.popularmoviesappstage1.GlideApp
import com.khinthirisoe.popularmoviesappstage1.R
import com.khinthirisoe.popularmoviesappstage1.core.config.ApiUrl
import com.khinthirisoe.popularmoviesappstage1.ui.main.model.Result

class DetailActivity : AppCompatActivity() {

    private lateinit var mPoster: ImageView
    private lateinit var mOverview: TextView
    private lateinit var mAverageRating: TextView
    private lateinit var mReleaseDate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initView()

        populateUI()
    }

    private fun initView() {
        mPoster = findViewById(R.id.img_poster)
        mOverview = findViewById(R.id.txt_overview)
        mAverageRating = findViewById(R.id.txt_average_rating)
        mReleaseDate = findViewById(R.id.txt_release_date)
    }

    private fun populateUI() {
        if (intent.hasExtra("data")) {
            val movieDetails = intent.getParcelableExtra<Result>("data")

            if (supportActionBar != null) {
                supportActionBar?.title = movieDetails.title
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setDisplayShowHomeEnabled(true)
            }

            GlideApp.with(this)
                .load(ApiUrl.POSTER_PATH + movieDetails.backdropPath)
                .placeholder(R.drawable.ic_movie)
                .into(mPoster)

            mOverview.text = movieDetails.overview
            mAverageRating.text = " " + movieDetails.voteAverage.toString()
            mReleaseDate.text = " " + movieDetails.releaseDate
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
