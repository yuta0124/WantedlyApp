package com.yuta0124.wantedlyapp.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yuta0124.wantedlyapp.core.design.system.theme.WantedlyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WantedlyApp(Modifier.fillMaxSize())
        }
    }
}

@Composable
fun WantedlyApp(modifier: Modifier = Modifier) {
    WantedlyAppTheme {
        Surface(modifier = modifier) {
            WantedlyNavHost()
        }
    }
}
