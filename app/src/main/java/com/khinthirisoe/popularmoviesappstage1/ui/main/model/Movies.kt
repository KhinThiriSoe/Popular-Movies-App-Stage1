package com.khinthirisoe.popularmoviesappstage1.ui.main.model

import com.google.gson.annotations.SerializedName

class Movies {
    @SerializedName("page")
    private var page: Int? = null
    @SerializedName("total_results")
    private var totalResults: Int? = null
    @SerializedName("total_pages")
    private var totalPages: Int? = null
    @SerializedName("results")
    private var results: ArrayList<Result>? = null

    fun getPage(): Int? {
        return page
    }

    fun setPage(page: Int?) {
        this.page = page
    }

    fun getTotalResults(): Int? {
        return totalResults
    }

    fun setTotalResults(totalResults: Int?) {
        this.totalResults = totalResults
    }

    fun getTotalPages(): Int? {
        return totalPages
    }

    fun setTotalPages(totalPages: Int?) {
        this.totalPages = totalPages
    }

    fun getResults(): ArrayList<Result>? {
        return results
    }

    fun setResults(results: ArrayList<Result>) {
        this.results = results
    }
}