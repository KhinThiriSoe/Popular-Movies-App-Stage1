package com.khinthirisoe.popularmoviesappstage1.core.service

import com.khinthirisoe.popularmoviesappstage1.ui.main.model.Movies
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(ApiUrl.DISCOVER_MOVIES)
    fun getSortedMoviesList(
        @Query("sort_by") sort_by: String,
        @Query("page") page: Int,
        @Query("api_key") api_key: String
    ): Single<Movies>

}