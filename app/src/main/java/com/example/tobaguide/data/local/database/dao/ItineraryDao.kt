package com.example.tobaguide.data.local.database.dao

import androidx.room.*
import com.example.tobaguide.data.local.entity.ItineraryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ItineraryDao {
    @Query("SELECT * FROM itinerary_table ORDER BY createdAt DESC")
    fun getAllItineraries(): Flow<List<ItineraryEntity>>

    @Query("SELECT * FROM itinerary_table WHERE id = :id")
    suspend fun getItineraryById(id: String): ItineraryEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItinerary(itinerary: ItineraryEntity)

    @Update
    suspend fun updateItinerary(itinerary: ItineraryEntity)

    @Delete
    suspend fun deleteItinerary(itinerary: ItineraryEntity)

    @Query("DELETE FROM itinerary_table WHERE id = :id")
    suspend fun deleteItineraryById(id: String)
}

