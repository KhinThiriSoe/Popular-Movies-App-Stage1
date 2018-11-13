package com.khinthirisoe.popularmoviesappstage1.core.di.module

import android.content.Context
import androidx.fragment.app.Fragment
import com.khinthirisoe.popularmoviesappstage1.core.di.context.ActivityContext
import dagger.Module
import dagger.Provides


@Module
class FragmentModule(private val mFragment: Fragment) {

    @Provides
    fun provideFragment(): Fragment {
        return mFragment
    }

    @Provides
    @ActivityContext
    fun provideFragmentContext(): Context {
        return mFragment.context!!
    }
}
