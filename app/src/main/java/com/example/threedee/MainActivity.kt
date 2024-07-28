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
import androidx.compose.material3.Scaffold
import com.example.threedee.ui.theme.ThreeDeeTheme
import com.example.threedee.view.BottomBar
import com.example.threedee.view.BottomBarScreen
import com.example.threedee.view.Home
import com.example.threedee.view.MultiplePhotoPicker
import com.example.threedee.view.SinglePhotoPicker

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThreeDeeTheme {

                val navController = rememberNavController()
                Scaffold(bottomBar = { BottomBar(navController = navController) }) {
                    innerPadding ->

                    Column(modifier = Modifier
                        .padding(innerPadding)
                        .padding(40.dp)) {
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

