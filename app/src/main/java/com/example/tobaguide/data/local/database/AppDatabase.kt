package com.example.tobaguide.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tobaguide.data.local.database.dao.ItineraryDao // Hanya import DAO yang ada
import com.example.tobaguide.data.local.entity.ItineraryEntity // Hanya import Entity yang ada

@Database(
    entities = [
        ItineraryEntity::class
        // TODO: Nanti, setelah Anda membuat UserEntity.kt, tambahkan UserEntity::class di sini
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    // Hanya sediakan fungsi untuk DAO yang sudah ada
    abstract fun itineraryDao(): ItineraryDao

    // TODO: Nanti, setelah Anda membuat UserDao.kt, tambahkan abstract fun userDao(): UserDao di sini
}