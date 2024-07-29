package com.example.threedee.view

import android.net.Uri
import android.webkit.WebView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
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
    }

}


