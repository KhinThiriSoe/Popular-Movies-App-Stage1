package com.khinthirisoe.popularmoviesappstage1.di.module

import android.app.Activity
import android.content.Context
import com.khinthirisoe.popularmoviesappstage1.di.context.ActivityContext
import com.khinthirisoe.popularmoviesappstage1.ui.overview.MainContract
import com.khinthirisoe.popularmoviesappstage1.ui.overview.model.MovieRepository
import com.khinthirisoe.popularmoviesappstage1.ui.overview.model.MovieApiService
import com.khinthirisoe.popularmoviesappstage1.ui.overview.presenter.MoviePresenter
import dagger.Module
import dagger.Provides


@Module
class ActivityModule(private val mActivity: Activity) {

    @Provides
    fun activityContext(): Context = mActivity

    @Provides
    @ActivityContext
    fun provideActivityContext(): Context {
        return mActivity
    }

    @Provides
    fun mainInteractor(apiService: MovieApiService): MovieRepository =
        MovieRepository(apiService)

    @Provides
    fun mainPresenter(interactor: MovieRepository): MainContract.Presenter =
        MoviePresenter(interactor)
}