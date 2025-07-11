package com.example.tobaguide.di

import com.example.tobaguide.domain.repository.IItineraryRepository // Pastikan import ini benar
import com.example.tobaguide.domain.repository.ItineraryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindItineraryRepository(
        itineraryRepository: ItineraryRepository
    ): IItineraryRepository

    // TODO: Tambahkan binding untuk repository lain di sini jika ada

}