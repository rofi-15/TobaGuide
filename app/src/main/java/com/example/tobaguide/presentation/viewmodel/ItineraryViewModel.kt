package com.example.tobaguide.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tobaguide.domain.model.ActivityEntry
import com.example.tobaguide.domain.model.ChecklistItem
import com.example.tobaguide.domain.model.DayPlan
import com.example.tobaguide.domain.model.ItineraryPlan
import com.example.tobaguide.domain.usecase.itinerary.DeleteItineraryUseCase
import com.example.tobaguide.domain.usecase.itinerary.GetItineraryByIdUseCase
import com.example.tobaguide.domain.usecase.itinerary.GetUserItinerariesUseCase
import com.example.tobaguide.domain.usecase.itinerary.SaveItineraryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class ItineraryListState(
    val savedItineraries: List<ItineraryPlan> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

data class ManualCreationState(
    val id: String? = null,
    val title: String = "Perjalanan Manual",
    val startDate: String = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE),
    val endDate: String = LocalDate.now().plusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE),
    val participantCount: Int = 1,
    val dailyPlans: List<DayPlan> = listOf(DayPlan()),
    val packingList: List<ChecklistItem> = emptyList(),
    val notes: List<ChecklistItem> = emptyList(),
    val isSaving: Boolean = false,
    val saveSuccess: Boolean = false
)

data class ItineraryDetailState(
    val itinerary: ItineraryPlan? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

@HiltViewModel
class ItineraryViewModel @Inject constructor(
    private val getUserItinerariesUseCase: GetUserItinerariesUseCase,
    private val saveItineraryUseCase: SaveItineraryUseCase,
    private val deleteItineraryUseCase: DeleteItineraryUseCase,
    private val getItineraryByIdUseCase: GetItineraryByIdUseCase
) : ViewModel() {

    private val _itineraryListState = MutableStateFlow(ItineraryListState())
    val itineraryListState: StateFlow<ItineraryListState> = _itineraryListState.asStateFlow()

    private val _manualCreationState = MutableStateFlow(ManualCreationState())
    val manualCreationState: StateFlow<ManualCreationState> = _manualCreationState.asStateFlow()

    private val _itineraryDetailState = MutableStateFlow(ItineraryDetailState())
    val itineraryDetailState: StateFlow<ItineraryDetailState> = _itineraryDetailState.asStateFlow()

    init {
        loadSavedItineraries()
    }

    private fun loadSavedItineraries() {
        viewModelScope.launch {
            _itineraryListState.update { it.copy(isLoading = true) }
            try {
                getUserItinerariesUseCase().collect { itineraries ->
                    _itineraryListState.update {
                        it.copy(isLoading = false, savedItineraries = itineraries)
                    }
                }
            } catch (e: Exception) {
                _itineraryListState.update { it.copy(isLoading = false, errorMessage = e.message) }
            }
        }
    }

    fun getItineraryDetails(id: String) {
        viewModelScope.launch {
            _itineraryDetailState.update { it.copy(isLoading = true) }
            try {
                val itinerary = getItineraryByIdUseCase(id)
                _itineraryDetailState.update {
                    it.copy(isLoading = false, itinerary = itinerary)
                }
            } catch (e: Exception) {
                _itineraryDetailState.update {
                    it.copy(isLoading = false, errorMessage = "Gagal memuat detail: ${e.message}")
                }
            }
        }
    }

    fun startEdit() {
        val itineraryToEdit = _itineraryDetailState.value.itinerary
        if (itineraryToEdit != null) {
            prepareForEdit(itineraryToEdit)
        }
    }

    private fun prepareForEdit(itineraryPlan: ItineraryPlan) {
        _manualCreationState.update {
            it.copy(
                id = itineraryPlan.id,
                title = itineraryPlan.title,
                startDate = itineraryPlan.startDate,
                endDate = itineraryPlan.endDate,
                participantCount = itineraryPlan.participantCount,
                dailyPlans = if (itineraryPlan.dailyPlans.isEmpty()) listOf(DayPlan()) else itineraryPlan.dailyPlans,
                packingList = itineraryPlan.packingList,
                notes = itineraryPlan.notes
            )
        }
    }

    fun onTitleChange(newTitle: String) {
        _manualCreationState.update { it.copy(title = newTitle) }
    }

    fun onParticipantCountChange(newCount: Int) {
        _manualCreationState.update { it.copy(participantCount = newCount.coerceAtLeast(1)) }
    }

    fun onAddDailyPlan() {
        _manualCreationState.update { currentState ->
            currentState.copy(dailyPlans = currentState.dailyPlans + DayPlan())
        }
    }

    // Date change methods - now working with String as expected by domain models
    fun onStartDateChange(newDate: String) {
        _manualCreationState.update { it.copy(startDate = newDate) }
    }

    fun onEndDateChange(newDate: String) {
        _manualCreationState.update { it.copy(endDate = newDate) }
    }

    // Overloaded methods to accept LocalDate and convert to String
    fun onStartDateChange(newDate: LocalDate) {
        onStartDateChange(newDate.format(DateTimeFormatter.ISO_LOCAL_DATE))
    }

    fun onEndDateChange(newDate: LocalDate) {
        onEndDateChange(newDate.format(DateTimeFormatter.ISO_LOCAL_DATE))
    }

    fun onRemoveDailyPlan() {
        _manualCreationState.update { currentState ->
            if (currentState.dailyPlans.size > 1) {
                currentState.copy(dailyPlans = currentState.dailyPlans.dropLast(1))
            } else currentState
        }
    }

    fun onDailyPlanDestinationChange(dayIndex: Int, newDestination: String) {
        _manualCreationState.update { currentState ->
            if (dayIndex < currentState.dailyPlans.size) {
                val newPlans = currentState.dailyPlans.toMutableList()
                newPlans[dayIndex] = newPlans[dayIndex].copy(destination = newDestination)
                currentState.copy(dailyPlans = newPlans)
            } else currentState
        }
    }

    fun onAddActivity(dayIndex: Int) {
        _manualCreationState.update { currentState ->
            if (dayIndex < currentState.dailyPlans.size) {
                val newPlans = currentState.dailyPlans.toMutableList()
                val currentActivities = newPlans[dayIndex].activities.toMutableList()
                currentActivities.add(ActivityEntry())
                newPlans[dayIndex] = newPlans[dayIndex].copy(activities = currentActivities)
                currentState.copy(dailyPlans = newPlans)
            } else currentState
        }
    }

    fun onRemoveActivity(dayIndex: Int, activityIndex: Int) {
        _manualCreationState.update { currentState ->
            if (dayIndex < currentState.dailyPlans.size) {
                val newPlans = currentState.dailyPlans.toMutableList()
                val currentActivities = newPlans[dayIndex].activities.toMutableList()
                if (activityIndex < currentActivities.size) {
                    currentActivities.removeAt(activityIndex)
                    newPlans[dayIndex] = newPlans[dayIndex].copy(activities = currentActivities)
                    currentState.copy(dailyPlans = newPlans)
                } else currentState
            } else currentState
        }
    }

    fun onActivityChange(dayIndex: Int, activityIndex: Int, newActivity: ActivityEntry) {
        _manualCreationState.update { currentState ->
            if (dayIndex < currentState.dailyPlans.size) {
                val newPlans = currentState.dailyPlans.toMutableList()
                val currentActivities = newPlans[dayIndex].activities.toMutableList()
                if (activityIndex < currentActivities.size) {
                    currentActivities[activityIndex] = newActivity
                    newPlans[dayIndex] = newPlans[dayIndex].copy(activities = currentActivities)
                    currentState.copy(dailyPlans = newPlans)
                } else currentState
            } else currentState
        }
    }

    fun onActivityCostChange(dayIndex: Int, activityIndex: Int, newCost: Int) {
        _manualCreationState.update { currentState ->
            if (dayIndex < currentState.dailyPlans.size) {
                val newPlans = currentState.dailyPlans.toMutableList()
                val currentActivities = newPlans[dayIndex].activities.toMutableList()
                if (activityIndex < currentActivities.size) {
                    currentActivities[activityIndex] = currentActivities[activityIndex].copy(cost = newCost)
                    newPlans[dayIndex] = newPlans[dayIndex].copy(activities = currentActivities)
                    currentState.copy(dailyPlans = newPlans)
                } else currentState
            } else currentState
        }
    }

    fun addPackingListItem(itemText: String) {
        if (itemText.isBlank()) return
        val newItem = ChecklistItem(text = itemText)
        _manualCreationState.update { it.copy(packingList = it.packingList + newItem) }
    }

    fun removePackingListItem(item: ChecklistItem) {
        _manualCreationState.update { it.copy(packingList = it.packingList - item) }
    }

    fun togglePackingListItem(item: ChecklistItem) {
        _manualCreationState.update { currentState ->
            val updatedList = currentState.packingList.map {
                if (it.id == item.id) it.copy(isChecked = !it.isChecked) else it
            }
            currentState.copy(packingList = updatedList)
        }
    }

    fun addNoteItem(itemText: String) {
        if (itemText.isBlank()) return
        val newItem = ChecklistItem(text = itemText)
        _manualCreationState.update { it.copy(notes = it.notes + newItem) }
    }

    fun removeNoteItem(item: ChecklistItem) {
        _manualCreationState.update { it.copy(notes = it.notes - item) }
    }

    fun toggleNoteItem(item: ChecklistItem) {
        _manualCreationState.update { currentState ->
            val updatedList = currentState.notes.map {
                if (it.id == item.id) it.copy(isChecked = !it.isChecked) else it
            }
            currentState.copy(notes = updatedList)
        }
    }

    fun saveCurrentManualPlan() {
        viewModelScope.launch {
            _manualCreationState.update { it.copy(isSaving = true) }
            val currentState = _manualCreationState.value
            val planId = currentState.id ?: System.currentTimeMillis().toString()
            val plan = ItineraryPlan(
                id = planId,
                title = currentState.title,
                startDate = currentState.startDate,
                endDate = currentState.endDate,
                participantCount = currentState.participantCount,
                dailyPlans = currentState.dailyPlans,
                packingList = currentState.packingList,
                notes = currentState.notes
            )
            try {
                saveItineraryUseCase(plan)
                _manualCreationState.update { it.copy(isSaving = false, saveSuccess = true) }
                loadSavedItineraries() // Refresh the list after saving
            } catch (e: Exception) {
                Log.e("ItineraryViewModel", "Gagal menyimpan", e)
                _manualCreationState.update { it.copy(isSaving = false) }
            }
        }
    }

    fun resetManualCreationState() {
        _manualCreationState.value = ManualCreationState()
    }

    fun clearSaveSuccess() {
        _manualCreationState.update { it.copy(saveSuccess = false) }
    }

    fun clearErrorMessage() {
        _itineraryListState.update { it.copy(errorMessage = null) }
        _itineraryDetailState.update { it.copy(errorMessage = null) }
    }

    // Utility methods for date conversion
    fun getStartDateAsLocalDate(): LocalDate? {
        return parseDate(_manualCreationState.value.startDate)
    }

    fun getEndDateAsLocalDate(): LocalDate? {
        return parseDate(_manualCreationState.value.endDate)
    }

    fun formatDateForDisplay(dateString: String): String {
        return parseDate(dateString)?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) ?: dateString
    }

    fun getCurrentDateAsString(): String {
        return LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)
    }

    fun parseDate(dateString: String): LocalDate? {
        return try {
            LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE)
        } catch (e: Exception) {
            try {
                LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            } catch (e2: Exception) {
                Log.e("ItineraryViewModel", "Unable to parse date: $dateString", e2)
                null
            }
        }
    }

    fun deleteItinerary(id: String) {
        viewModelScope.launch {
            try {
                deleteItineraryUseCase(id)
                // Refresh the list after deletion
                loadSavedItineraries()
            } catch (e: Exception) {
                _itineraryListState.update { it.copy(errorMessage = "Gagal menghapus: ${e.message}") }
            }
        }
    }
}