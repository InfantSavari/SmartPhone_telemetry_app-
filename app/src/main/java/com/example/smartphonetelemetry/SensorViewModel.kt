package com.example.smartphonetelemetry


import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SensorViewModel(): ViewModel(){
    private lateinit var _sensorManager: SensorManager
    private var _sensor: Sensor? = null
    private var _data: MutableStateFlow<FloatArray> = MutableStateFlow(floatArrayOf(0f,0f,0f,0f))
    private var activeListener : SensorEventListener? = null
    var sensorVal by mutableStateOf<Sensor?>(null)
    var sensorList: List<Sensor> by mutableStateOf(emptyList())
    var data: StateFlow<FloatArray> = _data.asStateFlow()
    fun getSensorList(context: Context) {
        if (!::_sensorManager.isInitialized) {
            _sensorManager =
                context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        }
        sensorList = _sensorManager.getSensorList(Sensor.TYPE_ALL)
    }
    fun setSensor(sensorValue: Sensor){
        _sensor = sensorValue
    }
    fun trackSensor(sensor: Sensor?) {

        if (!::_sensorManager.isInitialized) return   // ✅ prevents crash
        if (sensor == null) return                    // ✅ prevents crash

        stopTracking()

        _sensor = sensor
        sensorVal = sensor

        activeListener = object : SensorEventListener {

            override fun onSensorChanged(event: SensorEvent?) {
                event?.let {
                    _data.value = it.values.copyOf()
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }

        _sensorManager.registerListener(
            activeListener,
            sensor,
            SensorManager.SENSOR_DELAY_UI
        )
    }
    fun stopTracking(){
        activeListener?.let {
            _sensorManager.unregisterListener(it)
            activeListener = null
        }
        _data.value = floatArrayOf()
    }
}

