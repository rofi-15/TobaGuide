package com.example.tobaguide.domain.repository

import com.example.tobaguide.data.local.database.dao.ItineraryDao
import com.example.tobaguide.data.mapper.toDomain
import com.example.tobaguide.data.mapper.toEntity
import com.example.tobaguide.domain.model.ItineraryPlan
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItineraryRepository @Inject constructor(
    private val itineraryDao: ItineraryDao
) : IItineraryRepository {

    override fun getAllItineraries(): Flow<List<ItineraryPlan>> {
        return itineraryDao.getAllItineraries().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getItineraryById(id: String): ItineraryPlan? {
        return try {
            itineraryDao.getItineraryById(id)?.toDomain()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun saveItinerary(itinerary: ItineraryPlan): Result<Unit> {
        return try {
            itineraryDao.insertItinerary(itinerary.toEntity())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateItinerary(itinerary: ItineraryPlan): Result<Unit> {
        return try {
            itineraryDao.updateItinerary(itinerary.toEntity())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteItinerary(id: String): Result<Unit> {
        return try {
            itineraryDao.deleteItineraryById(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
