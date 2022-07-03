package com.wenger.mytestapplication.di

import com.wenger.mytestapplication.domain.IPrefsRepository
import com.wenger.mytestapplication.domain.PrefsRepository
import com.wenger.mytestapplication.ui.MyWebView
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideMyWebView(repository: IPrefsRepository): MyWebView = MyWebView(repository)

}