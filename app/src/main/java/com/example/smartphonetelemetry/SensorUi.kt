package com.example.smartphonetelemetry

import android.hardware.Sensor
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun SensorMonitorScreen(viewModel: SensorViewModel = viewModel()) {
    // Convert Flow to Compose State
    val sensorValues by viewModel.data.collectAsStateWithLifecycle()
    val selectedSensor: Sensor? = viewModel.sensorVal
    // Start tracking when this screen opens, stop when it closes
    DisposableEffect(selectedSensor) {
        viewModel.trackSensor(selectedSensor)
        onDispose { viewModel.stopTracking() }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Monitoring: ${selectedSensor?.name}", style = MaterialTheme.typography.headlineSmall)

        Spacer(Modifier.height(20.dp))

        // Dynamically display all values returned by the sensor
        sensorValues.forEachIndexed { index, value ->
            Text(text = "Axis $index: ${"%.2f".format(value)}", fontSize = 20.sp)
        }
    }
}