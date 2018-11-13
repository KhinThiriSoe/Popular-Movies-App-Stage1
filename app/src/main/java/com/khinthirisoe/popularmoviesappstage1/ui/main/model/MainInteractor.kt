package com.khinthirisoe.popularmoviesappstage1.ui.main.model

import com.khinthirisoe.popularmoviesappstage1.core.service.ApiService
import com.khinthirisoe.popularmoviesappstage1.ui.main.presenter.MainPresenter
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainInteractor @Inject
constructor(private val mApiService: ApiService) {

    fun loadMoviesList(sortedType: String, page: Int, key: String, listener: MainPresenter.OnMoviesListFetchListener) {
        mApiService.getSortedMoviesList(sortedType, page, key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Movies> {
                override fun onSuccess(movies: Movies) {
                    listener.onMoviesListFetched(movies.getResults()!!)
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