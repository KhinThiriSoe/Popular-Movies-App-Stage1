package com.khinthirisoe.popularmoviesappstage1.di.component

import android.content.Context
import com.khinthirisoe.popularmoviesappstage1.di.context.ApplicationContext
import com.khinthirisoe.popularmoviesappstage1.di.module.ActivityModule
import com.khinthirisoe.popularmoviesappstage1.di.scope.ActivityScope
import com.khinthirisoe.popularmoviesappstage1.ui.overview.MainContract
import com.khinthirisoe.popularmoviesappstage1.ui.overview.model.MovieRepository
import com.khinthirisoe.popularmoviesappstage1.ui.overview.view.MovieActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [(AppComponent::class)], modules = [ActivityModule::class])
interface ActivityComponent : AppComponent {

    @ApplicationContext
    override fun getContext(): Context

    fun mainInteractor(): MovieRepository

    fun mainPresenter(): MainContract.Presenter

    fun inject(activity: MovieActivity)
}