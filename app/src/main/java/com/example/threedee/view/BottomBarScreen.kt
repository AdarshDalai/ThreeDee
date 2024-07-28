package com.example.threedee.view

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.wear.compose.material.ContentAlpha


// Icon list is below
//https://fonts.google.com/icons

class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
){
}

@Composable
fun BottomBar(navController: NavHostController){

    var homeBottomElement: BottomBarScreen by remember{
        mutableStateOf(
            BottomBarScreen(
                route = "home",
                title = "home",
                icon = Icons.Default.Home
            )
        )
    }

    var singleBottomElement: BottomBarScreen by remember{
        mutableStateOf(
            BottomBarScreen(
                route = "single",
                title = "Single",
                icon = Icons.Default.Add
            )
        )
    }

    var multiBottomElement: BottomBarScreen by remember{
        mutableStateOf(
            BottomBarScreen(
                route = "multi",
                title = "Multi",
                icon = Icons.Default.Add
            )
        )
    }



    val screens = listOf(
        homeBottomElement,
        singleBottomElement,
        multiBottomElement
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination


    BottomAppBar(
        containerColor = Color.White
    ){
        screens.forEach{screen ->
            AddItem(screen = screen, currentDestination = currentDestination, navController = navController)

        }

    }

}


@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        label = { Text(text = screen.title) },
        icon = {
            Icon(imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}