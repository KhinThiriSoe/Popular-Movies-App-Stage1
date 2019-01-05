package com.khinthirisoe.popularmoviesappstage1.core.di.component

import android.content.Context
import com.khinthirisoe.popularmoviesappstage1.core.App
import com.khinthirisoe.popularmoviesappstage1.core.di.context.ApplicationContext
import com.khinthirisoe.popularmoviesappstage1.core.di.module.ApplicationModule
import com.khinthirisoe.popularmoviesappstage1.ui.main.model.MovieApiService
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
