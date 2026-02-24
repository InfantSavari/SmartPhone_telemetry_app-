package com.example.smartphonetelemetry

import android.content.Context
import android.hardware.Sensor
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigationevent.NavigationEvent


@Composable
fun HomeScreen(sensorViewModel: SensorViewModel = viewModel(), modifier: Modifier){
    val context = LocalContext.current
    val  str = sensorViewModel.bt
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.Start
    ) {
        Text("Sensors Active", fontWeight = FontWeight.Bold, fontSize = 30.sp, fontFamily = FontFamily.SansSerif, modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp))
        Button(
            onClick = {sensorViewModel.getSensorList(context)},
            shape = RoundedCornerShape(8.dp),
            enabled = true,
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Text(text = str)
        }
        Spacer(modifier= Modifier.height(10.dp))
        Scan(sensorViewModel)
    }
}


@Composable
fun Scan(sensorViewModel: SensorViewModel = viewModel()){
    val sensorList: List<Sensor> = sensorViewModel.sensorList
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.verticalScroll(enabled = true, state = rememberScrollState())
    ) {
        val len = sensorList.size
        for( i in 0 until len step 2){
            var temp: String = sensorList[i].name
            temp = temp.replace("Goldfish ","")
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                SensorCard(name = temp, image = null, null)
                if(i+1 < len){
                    temp = sensorList[i+1].name
                    temp = temp.replace("Goldfish ","")
                    SensorCard(name = temp, image = null, null)
                }
            }
        }
    }
}

@Composable
fun SensorCard(name: String, image : Painter?, click : NavigationEvent?){

    ElevatedButton(
        onClick = {},
        modifier = Modifier.padding(6.dp).size(height = 130.dp, width = 150.dp),
        shape = RoundedCornerShape(4.dp),

        ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(name, fontWeight = FontWeight.Bold, fontSize = 12.sp, textAlign = TextAlign.Center, color = Color.White, fontFamily = FontFamily.SansSerif)
            if(image != null){
                Image(image, contentDescription = "graph image ", modifier= Modifier.size(18.dp))
            }
        }
    }
}
