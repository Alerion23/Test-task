package com.wenger.mytestapplication.di

import com.wenger.mytestapplication.domain.IPrefsRepository
import com.wenger.mytestapplication.domain.PrefsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindPrefsRepository(repository: PrefsRepository): IPrefsRepository

}