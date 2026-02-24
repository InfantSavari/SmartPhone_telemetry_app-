package com.example.smartphonetelemetry

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

enum class scanning{
    Scanning,
    Scan
}
class SensorViewModel(): ViewModel(){
    private lateinit var _sensormanager: SensorManager;
    var sensorList: List<Sensor> by mutableStateOf(emptyList())
    var bt: String = scanning.Scan.name
    fun getSensorList(context: Context){
        bt = scanning.Scanning.name
        _sensormanager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorList = _sensormanager.getSensorList(Sensor.TYPE_ALL)
        bt = scanning.Scan.name
    }
}