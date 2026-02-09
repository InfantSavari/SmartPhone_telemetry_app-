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
import androidx.compose.ui.unit.*
import com.example.smartphonetelemetry.ui.theme.SmartPhoneTelemetryTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val sensorManager by lazy { getSystemService(Context.SENSOR_SERVICE) as SensorManager}
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartPhoneTelemetryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize().statusBarsPadding(),
                    contentColor = Color(0xFFEAEAEA)
                ) {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Column(
                            modifier = Modifier.padding(innerPadding),
                            verticalArrangement = Arrangement.SpaceAround,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text("Sensors Active", fontWeight = FontWeight.Bold, fontSize = 30.sp, fontFamily = FontFamily.SansSerif, modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp))
                            Scan(sensorManager)
                        }

                    }
                }

            }
        }
    }
}

@Composable
fun Scan(sensorManager: SensorManager){
    val sensorList : List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)
    val len = sensorList.size
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.verticalScroll(enabled = true, state = rememberScrollState(), reverseScrolling = true)
    ) {
        for( i in 0 until len step 2){
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                SensorCard(name = sensorList[i].name, image = null)
                SensorCard(name = sensorList[i+1].name, image = null)
            }
        }
    }
}

@Composable
fun SensorCard(name: String, image : Painter?){
    ElevatedCard(
        elevation = CardDefaults.cardElevation(5.dp),
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(name, fontWeight = FontWeight.Bold, fontSize = 20.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
        if(image != null){
            Image(image, contentDescription = "graph image ", modifier= Modifier.size(18.dp).align(Alignment.CenterHorizontally))
        }

    }
}




