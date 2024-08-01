package com.example.threedee.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng

class StreetViewViewModel : ViewModel() {
    private val _panoramaLocation = MutableLiveData<LatLng>()
    private val _imageUri = MutableLiveData<Uri?>()
    val imageUri: LiveData<Uri?> get() = _imageUri

    fun setImageUri(uri: Uri) {
        _imageUri.value = uri
    }

    fun convertImageTo360View(uri: Uri): LatLng {
        // Implement logic to convert image to a LatLng position
        // For demonstration, return a fixed LatLng
        return LatLng(37.7749, -122.4194)  // Example: San Francisco coordinates
    }
    val panoramaLocation: LiveData<LatLng> get() = _panoramaLocation

    fun setPanoramaLocation(latLng: LatLng) {
        _panoramaLocation.value = latLng
    }
}
