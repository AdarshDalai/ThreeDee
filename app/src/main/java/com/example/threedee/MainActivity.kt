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
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.threedee.ui.theme.ThreeDeeTheme
import com.example.threedee.view.BottomBar
import com.example.threedee.view.BottomBarScreen
import com.example.threedee.view.Home
import com.example.threedee.view.MultiplePhotoPicker
import com.example.threedee.view.MyApp
import com.example.threedee.view.PanoramaView
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
                        NavHost(navController = navController, startDestination = "multi") {
                            composable(
                                route = "home/{url}",
                                arguments = listOf(navArgument("url") { type = NavType.StringType })
                            ) { backStackEntry ->
                                val url: String = backStackEntry.arguments?.getString("url") ?: ""
                                println("from nav: $url")
                                Home(url)
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

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "myapp") {
        composable("myapp") { MyApp(navController) }
        composable(
            route = "panorama/{url}",
            arguments = listOf(navArgument("url") { type = NavType.StringType })
        ) { backStackEntry ->
            val url: String = backStackEntry.arguments?.getString("url") ?: ""
            println("from nav: $url")
            PanoramaView(navController = navController, url)
        }
    }
}
