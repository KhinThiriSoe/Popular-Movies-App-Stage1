package com.khinthirisoe.popularmoviesappstage1.ui.overview.model

import com.khinthirisoe.popularmoviesappstage1.core.config.ApiUrl
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET(ApiUrl.DISCOVER_MOVIES)
    fun getSortedMoviesList(
        @Query("sort_by") sort_by: String,
        @Query("page") page: Int,
        @Query("api_key") api_key: String
    ): Single<Movies>

}