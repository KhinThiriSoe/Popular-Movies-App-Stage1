package com.khinthirisoe.popularmoviesappstage1.core.di.component

import android.content.Context
import com.khinthirisoe.popularmoviesappstage1.core.di.context.ApplicationContext
import com.khinthirisoe.popularmoviesappstage1.core.di.module.ActivityModule
import com.khinthirisoe.popularmoviesappstage1.core.di.scope.ActivityScope
import com.khinthirisoe.popularmoviesappstage1.ui.main.MainContract
import com.khinthirisoe.popularmoviesappstage1.ui.main.model.MainInteractor
import com.khinthirisoe.popularmoviesappstage1.ui.main.view.MainActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [(AppComponent::class)], modules = [ActivityModule::class])
interface ActivityComponent : AppComponent {

    @ApplicationContext
    override fun getContext(): Context

    fun mainInteractor(): MainInteractor

    fun mainPresenter(): MainContract.Presenter

    fun inject(activity: MainActivity)
}