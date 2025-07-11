package com.example.tobaguide.navigation

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.tobaguide.presentation.screens.home.HomeScreen
import com.example.tobaguide.presentation.screens.itinerary.ItineraryScreen
import com.example.tobaguide.presentation.screens.profil.ProfileScreen
import com.example.tobaguide.presentation.screens.leaderboard.LeaderboardScreen
import com.example.tobaguide.presentation.screens.itinerary.manual.TravelPlanningScreen
import com.example.tobaguide.presentation.screens.home.SearchScreen
import com.example.tobaguide.presentation.screens.home.NotificationScreen
import com.example.tobaguide.presentation.screens.home.RatingScreen
import com.example.tobaguide.presentation.screens.home.DetailScreen
import com.example.tobaguide.presentation.screens.home.ReviewScreen
import com.example.tobaguide.presentation.screens.itinerary.ai.AITravelPlanningScreen
import com.example.tobaguide.presentation.component.profil.Edit_Profil.EditProfileScreen
import com.example.tobaguide.presentation.screens.itinerary.manual.DetailBiayaScreen
import com.example.tobaguide.presentation.screens.profil.ProfileBorderScreen
import com.example.tobaguide.ui.theme.TobaGuideTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            // Menggunakan helper function dari NavigationRoutes
            if (NavigationRoutes.shouldShowBottomNav(currentRoute)) {
                BottomNavBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavigationRoutes.HOME,
            modifier = Modifier.padding(innerPadding)
        ) {
            // Bottom Navigation Screens
            composable(NavigationRoutes.HOME) {
                HomeScreen(
                    onManualClick = {
                        navController.navigate(NavigationRoutes.TRAVEL_PLANNING)
                    },
                    onAIClick = {
                        navController.navigate(NavigationRoutes.AI_TRAVEL_PLANNING)
                    },
                    onSeeAllRecommendationsClick = {
                        navController.navigate(NavigationRoutes.SEARCH)
                    },
                    onRecommendationItemClick = { destinationId ->
                        navController.navigate(NavigationArgs.Routes.createDetailRoute(destinationId))
                    },
                    onNotificationClick = {
                        navController.navigate(NavigationRoutes.NOTIFICATION)
                    }
                )
            }

            composable(NavigationRoutes.ITINERARY) {
                ItineraryScreen(
                    onManualItineraryClick = {
                        navController.navigate(NavigationRoutes.TRAVEL_PLANNING)
                    },
                    onAIItineraryClick = {
                        navController.navigate(NavigationRoutes.AI_TRAVEL_PLANNING)
                    },
                    onItineraryItemClick = { itineraryId ->
                        // TODO: Buat route baru untuk detail itinerary
                        // Untuk sekarang, kita bisa print dulu untuk testing
                        Log.d("AppNavigation", "Itinerary clicked: $itineraryId")
                        // navController.navigate(NavigationRoutes.createItineraryDetailRoute(itineraryId))
                    }
                )
            }

            composable(NavigationRoutes.REWARD) {
                LeaderboardScreen()
            }

            composable(NavigationRoutes.PROFILE) {
                ProfileScreen(
                    onEditProfileClick = {
                        navController.navigate(NavigationRoutes.EDIT_PROFILE)
                    },
                    onProfileBorderClick = {
                        navController.navigate(NavigationRoutes.PROFILE_BORDER)
                    }
                )
            }

            // Profile Module Screens
            composable(NavigationRoutes.EDIT_PROFILE) {
                EditProfileScreen(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onSaveProfile = {
                        navController.popBackStack()
                    }
                )
            }

            composable(NavigationRoutes.PROFILE_BORDER) {
                ProfileBorderScreen(
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }

            // Home Module Screens
            composable(NavigationRoutes.SEARCH) {
                SearchScreen(
                    onNavigateToDetail = { destinationId ->
                        navController.navigate(NavigationArgs.Routes.createDetailRoute(destinationId))
                    },
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }

            composable(NavigationRoutes.NOTIFICATION) {
                NotificationScreen(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onRatingNavigate = {
                        navController.navigate(NavigationRoutes.RATING)
                    }
                )
            }

            composable(NavigationRoutes.RATING) {
                RatingScreen(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onSubmitRating = { rating, review ->
                        println("Rating submitted: $rating stars, Review: $review")
                        navController.popBackStack()
                    }
                )
            }

            composable(NavigationRoutes.REVIEW) {
                ReviewScreen(
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }

            composable(NavigationRoutes.DETAIL) { backStackEntry ->
                val destinationId = NavigationArgs.Args.getDestinationId(backStackEntry)
                DetailScreen(
                    destinationId = destinationId,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onShareClick = {
                        println("Share clicked for destination: $destinationId")
                    },
                    onNavigateToDetail = { newDestinationId ->
                        navController.navigate(NavigationArgs.Routes.createDetailRoute(newDestinationId))
                    },
                    onNavigateToReview = {
                        navController.navigate(NavigationRoutes.REVIEW)
                    }
                )
            }

            // Itinerary Module Screens
            composable(NavigationRoutes.TRAVEL_PLANNING) {
                TravelPlanningScreen(
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    onSavePlan = {
                        navController.navigate(NavigationRoutes.ITINERARY) {
                            popUpTo(NavigationRoutes.HOME) {
                                inclusive = false
                            }
                        }
                    },
                    onNavigateToDetailBiaya = {
                        navController.navigate(NavigationRoutes.DETAIL_BIAYA)
                    }
                )
            }

            composable(NavigationRoutes.AI_TRAVEL_PLANNING) {
                AITravelPlanningScreen(
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    onGenerateItinerary = {
                        navController.navigate(NavigationRoutes.ITINERARY) {
                            popUpTo(NavigationRoutes.HOME) {
                                inclusive = false
                            }
                        }
                    }
                )
            }

            composable(NavigationRoutes.DETAIL_BIAYA) {
                DetailBiayaScreen(
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppNavigationPreview() {
    TobaGuideTheme {
        AppNavigation()
    }
}