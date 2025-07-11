package com.example.tobaguide.navigation

import androidx.navigation.NavBackStackEntry

object NavigationArgs {

    const val DESTINATION_ID = "destinationId"
    const val USER_ID = "userId"
    const val ITINERARY_ID = "itineraryId"
    const val RATING_VALUE = "ratingValue"
    const val REVIEW_TEXT = "reviewText"

    object Routes {
        /**
         * Membuat route untuk detail destination dengan parameter destinationId
         */
        fun createDetailRoute(destinationId: String): String {
            return "detail/$destinationId"
        }

        // Get itinerary ID dari navigation arguments
        fun getItineraryId(backStackEntry: androidx.navigation.NavBackStackEntry): Long {
            return backStackEntry.arguments?.getString("itineraryId")?.toLongOrNull() ?: 0L
        }

        //  Create itinerary detail route
        fun createItineraryDetailRoute(itineraryId: Long): String {
            return "itinerary_detail/$itineraryId"
        }

        /**
         * Membuat route untuk itinerary dengan parameter (jika diperlukan di masa depan)
         */
        fun createItineraryDetailRoute(itineraryId: String): String {
            return "itinerary_detail/$itineraryId"
        }
        /**
         * Membuat route untuk user profile dengan parameter (jika diperlukan di masa depan)
         */
        fun createUserProfileRoute(userId: String): String {
            return "user_profile/$userId"
        }
    }
    /**
     * Helper functions untuk mengambil argument dari NavBackStackEntry
     */
    object Args {
        /**
         * Mengambil destinationId dari NavBackStackEntry
         */
        fun getDestinationId(backStackEntry: NavBackStackEntry): String {
            return backStackEntry.arguments?.getString(DESTINATION_ID) ?: ""
        }
        /**
         * Mengambil userId dari NavBackStackEntry
         */
        fun getUserId(backStackEntry: NavBackStackEntry): String {
            return backStackEntry.arguments?.getString(USER_ID) ?: ""
        }
        /**
         * Mengambil itineraryId dari NavBackStackEntry
         */
        fun getItineraryId(backStackEntry: NavBackStackEntry): String {
            return backStackEntry.arguments?.getString(ITINERARY_ID) ?: ""
        }
        /**
         * Mengambil rating value dari NavBackStackEntry
         */
        fun getRatingValue(backStackEntry: NavBackStackEntry): Float {
            return backStackEntry.arguments?.getString(RATING_VALUE)?.toFloatOrNull() ?: 0f
        }
        /**
         * Mengambil review text dari NavBackStackEntry
         */
        fun getReviewText(backStackEntry: NavBackStackEntry): String {
            return backStackEntry.arguments?.getString(REVIEW_TEXT) ?: ""
        }
    }

    /**
     * Navigation Options untuk berbagai kebutuhan
     */
    object Options {
        /**
         * Pop up to Home dengan inclusive false
         * Digunakan ketika ingin kembali ke Home tapi tetap keep Home di stack
         */
        const val POP_UP_TO_HOME_INCLUSIVE_FALSE = "home"

        /**
         * Pop up to Home dengan inclusive true
         * Digunakan ketika ingin clear semua stack sampai Home
         */
        const val POP_UP_TO_HOME_INCLUSIVE_TRUE = "home"

        /**
         * Clear semua back stack
         */
        const val CLEAR_BACK_STACK = "clear_all"
    }
}