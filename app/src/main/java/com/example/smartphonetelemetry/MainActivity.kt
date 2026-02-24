package com.example.smartphonetelemetry


import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.navigationevent.NavigationEvent
import com.example.smartphonetelemetry.ui.theme.SmartPhoneTelemetryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        val sensorManager by lazy { getSystemService(Context.SENSOR_SERVICE) as SensorManager}
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartPhoneTelemetryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize().statusBarsPadding(),
                    contentColor = Color(0xFFEAEAEA)
                ) {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        HomeScreen(modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}




