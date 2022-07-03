package com.wenger.mytestapplication.di

import android.content.Context
import com.wenger.mytestapplication.data.Prefs
import com.wenger.mytestapplication.data.managers.PreferenceDataManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun providePrefs(@ApplicationContext context: Context): Prefs = Prefs(context)

    @Singleton
    @Provides
    fun provideDataManager(prefs: Prefs): PreferenceDataManager = PreferenceDataManager(prefs)

}