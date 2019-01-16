package com.khinthirisoe.popularmoviesappstage1.core

import android.app.Application
import com.khinthirisoe.popularmoviesappstage1.di.component.AppComponent
import com.khinthirisoe.popularmoviesappstage1.di.component.DaggerAppComponent
import com.khinthirisoe.popularmoviesappstage1.di.module.ApplicationModule

class App : Application() {

    companion object {
        @JvmStatic
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().applicationModule(ApplicationModule(this)).build()
        appComponent.inject(this)

    }
}

