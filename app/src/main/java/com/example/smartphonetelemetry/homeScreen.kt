package com.example.smartphonetelemetry


import android.hardware.Sensor
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController


enum class Screen{
    HOME,
    SENSOR
}

@Composable
fun HomeScreen(
    sensorViewModel: SensorViewModel = viewModel(),
    modifier: Modifier,
    navController: NavController
) {
    val context = LocalContext.current
    val str = "Scan"
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                "Sensors Active",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier.padding(20.dp)
            )

            Button(
                onClick = { sensorViewModel.getSensorList(context) },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Text(text = str)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Scan(sensorViewModel, navController)
        }
}

@Composable
fun Scan(sensorViewModel: SensorViewModel = viewModel(), navController: NavController){
    val sensorList: List<Sensor> = sensorViewModel.sensorList.filter { !it.name.contains("uncalibrated", ignoreCase = true) }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
    ) {
        items(sensorList) { sensor ->
            var sensorName: String = sensor.name
            if(!sensorName.contains("uncalibrated",ignoreCase = true)){
                sensorName = sensorName.replace("Goldfish","")
                SensorCard(sensorName, navController, sensor)
            }
        }
    }
}

@Composable
fun SensorCard(name: String, navController: NavController, sensor: Sensor, viewModel: SensorViewModel = viewModel()){
    ElevatedButton(
        onClick = {
            viewModel.setSensor(sensor)
            navController.navigate(Screen.SENSOR.name)
        },
        modifier = Modifier.padding(6.dp).height(130.dp).fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),

        ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(name, fontWeight = FontWeight.Bold, fontSize = 12.sp, textAlign = TextAlign.Center, color = Color.White, fontFamily = FontFamily.SansSerif)
        }
    }
}
