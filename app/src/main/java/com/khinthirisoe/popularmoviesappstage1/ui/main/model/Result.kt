package com.khinthirisoe.popularmoviesappstage1.ui.main.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Result() : Parcelable {
    @SerializedName("vote_count")
    private var voteCount: Int? = null
    @SerializedName("id")
    private var id: Int? = null
    @SerializedName("video")
    private var video: Boolean? = null
    @SerializedName("vote_average")
    private var voteAverage: Double? = null
    @SerializedName("title")
    private var title: String? = null
    @SerializedName("popularity")
    private var popularity: Double? = null
    @SerializedName("poster_path")
    private var posterPath: String? = null
    @SerializedName("original_language")
    private var originalLanguage: String? = null
    @SerializedName("original_title")
    private var originalTitle: String? = null
    @SerializedName("genre_ids")
    private var genreIds: List<Int>? = null
    @SerializedName("backdrop_path")
    private var backdropPath: String? = null
    @SerializedName("adult")
    private var adult: Boolean? = null
    @SerializedName("overview")
    private var overview: String? = null
    @SerializedName("release_date")
    private var releaseDate: String? = null

    constructor(parcel: Parcel) : this() {
        voteCount = parcel.readValue(Int::class.java.classLoader) as? Int
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        video = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        voteAverage = parcel.readValue(Double::class.java.classLoader) as? Double
        title = parcel.readString()
        popularity = parcel.readValue(Double::class.java.classLoader) as? Double
        posterPath = parcel.readString()
        originalLanguage = parcel.readString()
        originalTitle = parcel.readString()
        backdropPath = parcel.readString()
        adult = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        overview = parcel.readString()
        releaseDate = parcel.readString()
    }

    fun getVoteCount(): Int? {
        return voteCount
    }

    fun setVoteCount(voteCount: Int?) {
        this.voteCount = voteCount
    }

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getVideo(): Boolean? {
        return video
    }

    fun setVideo(video: Boolean?) {
        this.video = video
    }

    fun getVoteAverage(): Double? {
        return voteAverage
    }

    fun setVoteAverage(voteAverage: Double?) {
        this.voteAverage = voteAverage
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun getPopularity(): Double? {
        return popularity
    }

    fun setPopularity(popularity: Double?) {
        this.popularity = popularity
    }

    fun getPosterPath(): String? {
        return posterPath
    }

    fun setPosterPath(posterPath: String) {
        this.posterPath = posterPath
    }

    fun getOriginalLanguage(): String? {
        return originalLanguage
    }

    fun setOriginalLanguage(originalLanguage: String) {
        this.originalLanguage = originalLanguage
    }

    fun getOriginalTitle(): String? {
        return originalTitle
    }

    fun setOriginalTitle(originalTitle: String) {
        this.originalTitle = originalTitle
    }

    fun getGenreIds(): List<Int>? {
        return genreIds
    }

    fun setGenreIds(genreIds: List<Int>) {
        this.genreIds = genreIds
    }

    fun getBackdropPath(): String? {
        return backdropPath
    }

    fun setBackdropPath(backdropPath: String) {
        this.backdropPath = backdropPath
    }

    fun getAdult(): Boolean? {
        return adult
    }

    fun setAdult(adult: Boolean?) {
        this.adult = adult
    }

    fun getOverview(): String? {
        return overview
    }

    fun setOverview(overview: String) {
        this.overview = overview
    }

    fun getReleaseDate(): String? {
        return releaseDate
    }

    fun setReleaseDate(releaseDate: String) {
        this.releaseDate = releaseDate
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(voteCount)
        parcel.writeValue(id)
        parcel.writeValue(video)
        parcel.writeValue(voteAverage)
        parcel.writeString(title)
        parcel.writeValue(popularity)
        parcel.writeString(posterPath)
        parcel.writeString(originalLanguage)
        parcel.writeString(originalTitle)
        parcel.writeString(backdropPath)
        parcel.writeValue(adult)
        parcel.writeString(overview)
        parcel.writeString(releaseDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Result> {
        override fun createFromParcel(parcel: Parcel): Result {
            return Result(parcel)
        }

        override fun newArray(size: Int): Array<Result?> {
            return arrayOfNulls(size)
        }
    }
}