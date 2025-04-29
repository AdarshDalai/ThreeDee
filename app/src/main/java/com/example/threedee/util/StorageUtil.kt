package com.example.threedee.util

import android.content.Context
import android.net.Uri
import com.example.threedee.MainActivity
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.Response
import java.io.File
import java.io.IOException

class StorageUtil{


    companion object {

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


    }
}
