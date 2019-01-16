package com.khinthirisoe.popularmoviesappstage1.di.component

import android.content.Context
import com.khinthirisoe.popularmoviesappstage1.core.App
import com.khinthirisoe.popularmoviesappstage1.di.context.ApplicationContext
import com.khinthirisoe.popularmoviesappstage1.di.module.ApplicationModule
import com.khinthirisoe.popularmoviesappstage1.ui.overview.model.MovieApiService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApplicationModule::class)])
interface AppComponent {

    @ApplicationContext
    fun getContext(): Context

    fun inject(application: App)

    fun app(): App

    fun ApiService(): MovieApiService

}
