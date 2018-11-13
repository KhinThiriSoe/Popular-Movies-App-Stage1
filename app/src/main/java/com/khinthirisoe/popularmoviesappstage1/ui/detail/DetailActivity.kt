package com.khinthirisoe.popularmoviesappstage1.ui.detail

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.khinthirisoe.popularmoviesappstage1.GlideApp
import com.khinthirisoe.popularmoviesappstage1.R
import com.khinthirisoe.popularmoviesappstage1.core.service.ApiUrl
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
                supportActionBar?.title = movieDetails.getTitle()
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setDisplayShowHomeEnabled(true)
            }

            GlideApp.with(this)
                .load(ApiUrl.POSTER_PATH + movieDetails.getBackdropPath())
                .placeholder(R.drawable.ic_movie)
                .into(mPoster)

            mOverview.text = movieDetails.getOverview()
            mAverageRating.text = " " + movieDetails.getVoteAverage().toString()
            mReleaseDate.text = " " + movieDetails.getReleaseDate()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
