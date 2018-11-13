package com.khinthirisoe.popularmoviesappstage1.core.di.module

import android.app.Activity
import android.content.Context
import com.khinthirisoe.popularmoviesappstage1.core.di.context.ActivityContext
import com.khinthirisoe.popularmoviesappstage1.core.service.ApiService
import com.khinthirisoe.popularmoviesappstage1.ui.main.MainContract
import com.khinthirisoe.popularmoviesappstage1.ui.main.model.MainInteractor
import com.khinthirisoe.popularmoviesappstage1.ui.main.presenter.MainPresenter
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
    fun mainInteractor(apiService: ApiService): MainInteractor = MainInteractor(apiService)

    @Provides
    fun mainPresenter(interactor: MainInteractor): MainContract.Presenter = MainPresenter(interactor)
}