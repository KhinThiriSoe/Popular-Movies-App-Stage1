package com.khinthirisoe.popularmoviesappstage1.ui.overview.model

import com.khinthirisoe.popularmoviesappstage1.ui.overview.presenter.MoviePresenter
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieRepository @Inject
constructor(private val mApiService: MovieApiService) {

    fun loadMoviesList(sortedType: String, page: Int, key: String, listener: MoviePresenter.OnMoviesListFetchListener) {
        mApiService.getSortedMoviesList(sortedType, page, key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Movies> {
                override fun onSuccess(movies: Movies) {
                    listener.onMoviesListFetched(movies.results)
                }

                override fun onSubscribe(d: Disposable) {
                    listener.onListsFetchDisposable(d)
                }

                override fun onError(e: Throwable) {
                    listener.onListsFetchFailed()
                }

            })
    }
}