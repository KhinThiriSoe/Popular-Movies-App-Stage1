package com.khinthirisoe.popularmoviesappstage1.ui.overview.presenter

import com.khinthirisoe.popularmoviesappstage1.ui.overview.MainContract
import com.khinthirisoe.popularmoviesappstage1.ui.overview.model.MovieRepository
import com.khinthirisoe.popularmoviesappstage1.ui.overview.model.Result
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MoviePresenter @Inject
constructor(var interactor: MovieRepository) :
    MainContract.Presenter {
    var view: MainContract.View? = null
    var moviesList: ArrayList<Result>? = null
    var disposable: Disposable? = null

    override fun loadMoviesList(sortedType: String, page: Int, key: String) {
        if (view != null) view?.showProgress()
        interactor.loadMoviesList(sortedType, page, key, object :
            OnMoviesListFetchListener {
            override fun onMoviesListFetched(lists: ArrayList<Result>) {
                this@MoviePresenter.moviesList = lists
                if (view != null) {
                    view?.showMoviesList(lists)
                    view?.hideProgress()
                }
            }

            override fun onListsFetchFailed() {
                if (view != null) {
                    view?.showMessage("Fetch failed")
                    view?.hideProgress()
                }

            }

            override fun onListsFetchDisposable(d: Disposable) {
                disposable = d
            }
        })
    }

    override fun onAttachView(view: MainContract.View) {
        this.view = view
    }

    override fun onDetachView() {
        disposable?.dispose()
        this.view = null
    }

    interface OnMoviesListFetchListener {
        fun onMoviesListFetched(lists: ArrayList<Result>)

        fun onListsFetchFailed()

        fun onListsFetchDisposable(d: Disposable)

    }
}