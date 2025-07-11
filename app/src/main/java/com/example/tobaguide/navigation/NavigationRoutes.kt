package com.example.tobaguide.navigation

object NavigationRoutes {

    const val HOME = "home"
    const val ITINERARY = "itinerary"
    const val REWARD = "reward"
    const val PROFILE = "profile"
    const val TRAVEL_PLANNING = "travel_planning"
    const val AI_TRAVEL_PLANNING = "ai_travel_planning"
    const val DETAIL_BIAYA = "detail_biaya"
    const val SEARCH = "search"
    const val NOTIFICATION = "notification"
    const val RATING = "rating"
    const val REVIEW = "review"
    const val DETAIL = "detail/{destinationId}"
    const val EDIT_PROFILE = "edit_profile"
    const val PROFILE_BORDER = "profile_border"

    const val ITINERARY_DETAIL = "itinerary_detail/{itineraryId}"

    val ROUTES_WITHOUT_BOTTOM_NAV = listOf(
        TRAVEL_PLANNING,
        AI_TRAVEL_PLANNING,
        EDIT_PROFILE,
        PROFILE_BORDER,
        DETAIL_BIAYA,
        SEARCH,
        NOTIFICATION,
        RATING,
        REVIEW
    )

    fun createItineraryDetailRoute(itineraryId: Long): String {
        return "itinerary_detail/$itineraryId"
    }

    fun shouldShowBottomNav(currentRoute: String?): Boolean {
        return when (currentRoute) {
            HOME, ITINERARY, REWARD, PROFILE -> true
            else -> false
        }
    }
}