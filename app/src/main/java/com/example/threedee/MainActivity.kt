package com.example.threedee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.Scaffold
import com.example.photopicker.View.Home
import com.example.photopicker.View.MultiplePhotoPicker
import com.example.photopicker.View.SinglePhotoPicker
import com.example.threedee.ui.theme.ThreeDeeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThreeDeeTheme {
                Scaffold {
                val navController = rememberNavController()
                    Column(modifier = Modifier.padding(50.dp)) {
                        NavHost(navController = navController, startDestination = "single") {
                            composable("home") {
                                Home()
                            }
                            composable("single") {
                                SinglePhotoPicker()
                            }
                            composable("multi") {
                                MultiplePhotoPicker()
                            }
                        }
                    }
                }
            }
        }
    }
}

