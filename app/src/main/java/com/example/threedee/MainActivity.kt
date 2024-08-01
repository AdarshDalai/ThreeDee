package com.example.threedee

import android.R
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.threedee.ui.screens.UploadScreen
import com.example.threedee.ui.theme.ThreeDeeTheme
import com.panoramagl.PLImage
import com.panoramagl.PLManager
import com.panoramagl.PLSphericalPanorama
import com.panoramagl.utils.PLUtils


class MainActivity : ComponentActivity() {
    private var plManager: PLManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        plManager = PLManager(this)
        enableEdgeToEdge()
        setContent {
            ThreeDeeTheme {
                UploadScreen()
            }
        }
        plManager!!.onCreate()

        val panorama = PLSphericalPanorama()
        panorama.camera.lookAt(30.0f, 90.0f)

        //panorama.setImage(PLImage(PLUtils.getBitmap(this, ), false))
        plManager!!.panorama = panorama
    }
    override fun onResume() {
        super.onResume()
        plManager!!.onResume()
    }

    override fun onPause() {
        plManager!!.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        plManager!!.onDestroy()
        super.onDestroy()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return plManager!!.onTouchEvent(event!!)
    }
}

