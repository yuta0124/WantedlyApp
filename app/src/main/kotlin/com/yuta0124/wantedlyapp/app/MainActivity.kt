package com.yuta0124.wantedlyapp.app

import BottomNavigation
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.yuta0124.wantedlyapp.core.design.system.theme.WantedlyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
            val navController = rememberNavController()
            val recruitmentsLazyListState = rememberLazyListState()

            Scaffold(
                bottomBar = {
                    BottomNavigation(
                        recruitmentsLazyListState = recruitmentsLazyListState,
                        navController = navController,
                    )
                }
            ) { innerPadding ->
                WantedlyNavHost(
                    modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding()),
                    recruitmentsLazyListState = recruitmentsLazyListState,
                    navHostController = navController,
                )
            }
        }
    }
}
