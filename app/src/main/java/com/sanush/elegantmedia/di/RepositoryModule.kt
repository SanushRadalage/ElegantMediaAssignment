package com.sanush.elegantmedia.di

import com.sanush.elegantmedia.network.RetrofitService
import com.sanush.elegantmedia.repository.Repository
import com.sanush.elegantmedia.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(
        retrofitService: RetrofitService
    ): Repository {
        return RepositoryImpl(retrofitService)
    }

}