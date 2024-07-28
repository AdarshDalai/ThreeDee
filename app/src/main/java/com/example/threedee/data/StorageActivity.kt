package com.example.threedee.data

import com.google.firebase.Firebase
import com.google.firebase.storage.storage

class StorageActivity {
    val storage = Firebase.storage
    val storageRef = storage.reference
    val imagesRef = storageRef.child("images")
    val imageRef = imagesRef.child("image1.jpg")
    val downloadUrl = imageRef.downloadUrl

}