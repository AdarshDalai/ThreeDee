package com.example.threedee.view

import android.content.Context
import android.net.Uri
import android.webkit.WebView
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.threedee.MainActivity
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.Response
import okio.IOException
import java.io.File

class random {
}


@Composable
fun MyApp(navController: NavController) {
    var imageUris by remember {
        mutableStateOf(listOf<Uri>())
    }
    var panoramaUrl by remember {
        mutableStateOf<String?>(null)
    }

    val context = LocalContext.current
    val imagePickerLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetMultipleContents()) { uris ->
            imageUris = uris
        }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { imagePickerLauncher.launch("image/*") }) {
            Text(text = "Pick images")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (imageUris.isNotEmpty()) {
            Button(onClick = { uploadImages(context, imageUris) { url -> panoramaUrl = url } }) {
                Text(text = "Upload images")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        if (panoramaUrl != null) {
            Button(onClick = { navController.navigate("panorama/${Uri.encode(panoramaUrl)}") }) {
                Text(text = "Click to see panorama")
            }
        }
    }
}

fun uploadImages(context: Context, imageUris: List<Uri>, onResult: (String) -> Unit) {
    val client = OkHttpClient()
    val requestBody = MultipartBody.Builder().setType(MultipartBody.FORM).apply {
        imageUris.forEach { uri ->
//                val file = File(uri)
//                val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
            val inputStream = context.contentResolver.openInputStream(uri)
            val file = File.createTempFile("uploads", ".jpeg", context.cacheDir)
            inputStream?.use { ip ->
                file.outputStream().use { op ->
                    ip.copyTo(op)
                }
            }
            val requestFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            addFormDataPart("images", file.name, requestFile)
        }
    }.build()

    val request = Request.Builder()
        .url("https://81db4ec0-bb4d-4aac-8c6a-a04cf553573d-00-1coxric1gxo3d.janeway.replit.dev/upload")
        .post(requestBody)
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            if (response.isSuccessful) {
                val url = response.body?.string()?.let { parsePanoramaUrl(it) }
                println(url)
                if (url != null) {
                    (context as MainActivity).runOnUiThread {
                        onResult(url)
                    }
                }
            }
        }

    })
}


fun parsePanoramaUrl(response: String): String? {
    return Regex("""href="(.*?)"""").find(response)?.groupValues?.get(1)
}

@Composable
fun PanoramaView(navController: NavController, url: String) {
    AndroidView(factory = { context ->
        WebView(context).apply {
            settings.javaScriptEnabled = true
            loadUrl(url)
        }
    }, modifier = Modifier.fillMaxSize())
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

