package com.example.parkingapi



import java.util.*


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.parkingapi.database.DatabaseParking
import com.example.parkingapi.viewmodel.ParkingListViewModel

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.android.viewmodel.ext.android.viewModel

import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private val TAG = MapsActivity::class.java.simpleName
    private val viewModel: ParkingListViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        //val sydney = LatLng(-34.0, 151.0)
        //mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        addMarkers(mMap)
    }


private fun addMarkers(googleMap: GoogleMap) {
    viewModel.parkingListResults.observe(this,
        Observer<List<DatabaseParking>> { parking ->
            parking.apply {
                parking.forEach {
                    val latLng = LatLng(it.lat, it.lng)
                    val snippet = String.format(
                        Locale.getDefault(),
                        "Lat: %1$.5f, Long: %2$.5f",
                        latLng.latitude,
                        latLng.longitude
                    )
                    if (it.is_reserved.equals(false)) {
                        val marker = googleMap.addMarker(
                            MarkerOptions()
                                .position(latLng)
                                .snippet(snippet)
                                .title("Available space")
                                .icon(
                                    BitmapDescriptorFactory.defaultMarker(
                                        BitmapDescriptorFactory.HUE_GREEN
                                    )
                                )
                        )
                    } else {
                        val marker = googleMap.addMarker(
                            MarkerOptions()
                                .position(latLng)
                                .snippet(snippet)
                                .title("Occupied space")
                                .icon(
                                    BitmapDescriptorFactory.defaultMarker(
                                        BitmapDescriptorFactory.HUE_RED
                                    )
                                )
                        )
                    }
                }

            }
        })
}}