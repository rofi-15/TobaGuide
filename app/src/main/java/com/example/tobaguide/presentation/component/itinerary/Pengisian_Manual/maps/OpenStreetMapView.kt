package com.example.tobaguide.presentation.component.itinerary.Pengisian_Manual.maps

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

@Composable
fun OpenStreetMapView(
    latitude: Double,
    longitude: Double,
    locationName: String,
    modifier: Modifier = Modifier,
    onMapReady: () -> Unit = {}
) {
    val context = LocalContext.current
    val TAG = "OpenStreetMapView"

    Log.d(TAG, "OpenStreetMapView called with lat: $latitude, lng: $longitude, name: $locationName")

    // Initialize OSMDroid configuration
    LaunchedEffect(Unit) {
        try {
            Configuration.getInstance().load(context, context.getSharedPreferences("osmdroid", 0))
            Configuration.getInstance().userAgentValue = "TobaGuide"
            Log.d(TAG, "OSMDroid configuration loaded successfully")
        } catch (e: Exception) {
            Log.e(TAG, "Error loading OSMDroid configuration: ${e.message}")
        }
    }

    AndroidView(
        factory = { ctx ->
            Log.d(TAG, "Creating MapView...")
            try {
                MapView(ctx).apply {
                    Log.d(TAG, "MapView created, setting up...")

                    setTileSource(TileSourceFactory.MAPNIK)
                    setMultiTouchControls(true)
                    setBuiltInZoomControls(false)

                    // Set map center and zoom
                    val geoPoint = GeoPoint(latitude, longitude)
                    controller.setZoom(15.0)
                    controller.setCenter(geoPoint)

                    Log.d(TAG, "Map centered at: $geoPoint with zoom 15")

                    // Add marker
                    val marker = Marker(this).apply {
                        position = geoPoint
                        title = locationName
                        setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                    }
                    overlays.add(marker)

                    Log.d(TAG, "Marker added at position: $geoPoint")

                    // Call onMapReady after setup
                    post {
                        Log.d(TAG, "Map setup completed, calling onMapReady")
                        onMapReady()
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error creating MapView: ${e.message}")
                // Return empty MapView if error occurs
                MapView(ctx)
            }
        },
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(8.dp)),
        update = { view ->
            Log.d(TAG, "Updating MapView...")
            try {
                // Update map when coordinates change
                val geoPoint = GeoPoint(latitude, longitude)
                view.controller.setCenter(geoPoint)
                view.overlays.clear()

                val marker = Marker(view).apply {
                    position = geoPoint
                    title = locationName
                    setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                }
                view.overlays.add(marker)
                view.invalidate()

                Log.d(TAG, "MapView updated successfully")
            } catch (e: Exception) {
                Log.e(TAG, "Error updating MapView: ${e.message}")
            }
        }
    )
}