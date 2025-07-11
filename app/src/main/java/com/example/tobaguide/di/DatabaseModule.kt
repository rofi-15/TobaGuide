package com.example.tobaguide.di

import android.content.Context
import androidx.room.Room
import com.example.tobaguide.data.local.database.AppDatabase // Pastikan import ini benar
import com.example.tobaguide.data.local.database.dao.ItineraryDao // Pastikan import ini benar
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "toba_guide.db" // Anda bisa menamai database Anda apa saja
        ).build()
    }
    @Provides
    fun provideItineraryDao(database: AppDatabase): ItineraryDao {
        return database.itineraryDao() // Asumsi fungsi di AppDatabase Anda bernama itineraryDao()
    }
}
