package com.khinthirisoe.popularmoviesappstage1.di.component

import android.content.Context
import com.khinthirisoe.popularmoviesappstage1.di.context.ActivityContext
import com.khinthirisoe.popularmoviesappstage1.di.module.FragmentModule
import com.khinthirisoe.popularmoviesappstage1.di.scope.FragmentScope
import dagger.Component

@FragmentScope
@Component(dependencies = [(AppComponent::class)], modules = [(FragmentModule::class)])
interface FragmentComponent {

    @get:ActivityContext
    var context: Context
}