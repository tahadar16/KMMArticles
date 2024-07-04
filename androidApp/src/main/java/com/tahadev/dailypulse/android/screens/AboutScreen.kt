package com.tahadev.dailypulse.android.screens

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tahadev.dailypulse.Platform

@Composable
fun AboutScreen(modifier: Modifier = Modifier, onBackPressed: () -> Unit) {
    Column {
        TopBar(onBackPressed)
        Body()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(onBackPressed: () -> Unit) {
    TopAppBar(
        title = { Text(text = "About Device") },
        navigationIcon = {
            IconButton(onClick = { onBackPressed() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back button"
                )
            }
        }
    )
}

@Composable
private fun Body(modifier: Modifier = Modifier) {
    val deviceInfo = getDeviceInfo()

    LazyColumn(
        Modifier
            .fillMaxSize()
            .safeContentPadding()
            .padding(horizontal = 16.dp),
        verticalArrangement = spacedBy(8.dp)
    ) {
        items(deviceInfo) { infoItem ->
            InfoItem(label = infoItem.first, value = infoItem.second)
        }
    }
}

@Composable
private fun InfoItem(label: String, value: String) {
    Column(Modifier.fillMaxWidth(), verticalArrangement = spacedBy(4.dp)) {
        Text(text = label, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black
        )
        Divider()
    }
}

private fun getDeviceInfo(): List<Pair<String, String>> {
    val platform = Platform()
    return listOf(
        Pair("Operating System", "${platform.osName} ${platform.osVersion}"),
        Pair("Device", platform.deviceModel),
        Pair("Density", "${platform.screenDensity}")
    )
}

@Preview(showBackground = true)
@Composable
private fun TopBarPrev() {
    TopBar(){}
}

@Preview(showBackground = true)
@Composable
private fun BodyPrev() {
    Body()
}

@Preview(showBackground = true)
@Composable
private fun AboutScreenPrev() {
    AboutScreen() {}
}