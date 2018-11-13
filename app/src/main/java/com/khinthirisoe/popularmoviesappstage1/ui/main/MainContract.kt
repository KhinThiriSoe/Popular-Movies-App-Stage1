package com.khinthirisoe.popularmoviesappstage1.ui.main

import com.khinthirisoe.popularmoviesappstage1.ui.main.model.Result

class MainContract {
    interface View {
        fun showMoviesList(lists: ArrayList<Result>)

        fun showMessage(message: String)

        fun showProgress()

        fun hideProgress()
    }

    interface Presenter {

        fun onAttachView(view: View)

        fun onDetachView()

        fun loadMoviesList(sortedType: String, page: Int, key: String)
    }
}
