package com.example.threedee.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadScreen() {
    val retrievedFile = remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "UploadImage")
            },
                navigationIcon = {
                    Icon(Icons.Default.Menu, contentDescription = "Backbutton")
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .padding(30.dp)
        ) {
            Row {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row {
                            Icon(Icons.Default.Add, contentDescription = "Upload")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Row {
                            Text(text = "Upload Image File", fontSize = 20.sp)
                        }
                    }
                }
            }
            if (retrievedFile.value.isNotEmpty()) {
                Row() {
                    ViewCard()
                }
            }
        }
    }
}

@Composable
fun ViewCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
    ) {

    }
}


@Preview(showBackground = true)
@Composable
fun UploadScreenPreview() {
    UploadScreen()
}

@Preview(showBackground = true)
@Composable
fun ViewCardPreview() {
    ViewCard()
}