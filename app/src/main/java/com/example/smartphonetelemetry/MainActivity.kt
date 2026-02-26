package com.example.smartphonetelemetry



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smartphonetelemetry.ui.theme.SmartPhoneTelemetryTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartPhoneTelemetryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize().statusBarsPadding(),
                    contentColor = Color(0xFFEAEAEA)
                ) {
                    MainScreen()
                }
            }
        }
    }
}


@Composable
fun MainScreen(){
    val navController = rememberNavController()
    Scaffold(modifier = Modifier.systemBarsPadding().fillMaxSize()) { innerPadding ->
        NavHost(navController = navController, startDestination = Screen.HOME.name, modifier = Modifier.padding(innerPadding)) {
            composable(route = Screen.HOME.name){
                HomeScreen(modifier = Modifier.fillMaxSize(), navController = navController)
            }
            composable(route = Screen.SENSOR.name){
                SensorMonitorScreen()
            }
        }
    }
}



