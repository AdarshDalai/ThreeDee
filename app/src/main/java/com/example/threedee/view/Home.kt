package com.example.threedee.view

import android.net.Uri
import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.threedee.MainViewModel


@Composable
fun Home(viewModel: MainViewModel) {
    var panoramaUrl = viewModel.homeUrl.value ?: ""
    println(panoramaUrl)
    Column {

        Button(onClick = { panoramaUrl = Uri.encode(panoramaUrl) }) {
            Text(text = "Click to see panorama")
        }
        Text("Uploaded Image")

        Spacer(modifier = Modifier.height(10.dp))

        if (panoramaUrl.isNotEmpty()) {
            println(panoramaUrl)
            AndroidView(factory = { context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    loadUrl(panoramaUrl)
                }
            }, modifier = Modifier.fillMaxSize())
        }
        /*Column(modifier = Modifier
            .fillMaxSize(),verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            JoystickFab(
                onClick = { /* TODO: Handle joystick click */ },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
            )
        }*/
    }
}


@Composable
fun JoystickFab(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier
            .size(50.dp)
            .border(4.dp, Color.White, CircleShape), // Add a border to make it look like a joystick
        shape = CircleShape,
        content = {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(Color.White, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Filled.LocationOn,
                    contentDescription = "Joystick",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        })
}