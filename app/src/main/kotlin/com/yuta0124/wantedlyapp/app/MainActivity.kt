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
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.scene.rememberSceneSetupNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.yuta0124.wantedlyapp.core.design.system.theme.WantedlyAppTheme
import com.yuta0124.wantedlyapp.feature.favorite.navigation.FavoriteNavKey
import com.yuta0124.wantedlyapp.feature.favorite.navigation.favoritesEntry
import com.yuta0124.wantedlyapp.feature.recruitmentdetail.navigation.RecruitmentDetailNavKey
import com.yuta0124.wantedlyapp.feature.recruitmentdetail.navigation.recruitmentDetailEntry
import com.yuta0124.wantedlyapp.feature.recruitments.navigation.RecruitmentsNavKey
import com.yuta0124.wantedlyapp.feature.recruitments.navigation.recruitmentsEntry
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
            val recruitmentsLazyListState = rememberLazyListState()
            val backStack = rememberNavBackStack(RecruitmentsNavKey)

            Scaffold(
                bottomBar = {
                    BottomNavigation(
                        recruitmentsLazyListState = recruitmentsLazyListState,
                        currentDestination = backStack.lastOrNull(),
                        navigateTo = backStack::add,
                    )
                }
            ) { innerPadding ->
                NavDisplay(
                    modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding()),
                    backStack = backStack,
                    entryDecorators = listOf(
                        rememberSceneSetupNavEntryDecorator(),
                        rememberSavedStateNavEntryDecorator(),
                        rememberViewModelStoreNavEntryDecorator(),
                    ),
                    entryProvider = entryProvider {
                        recruitmentsEntry(
                            recruitmentsLazyListState = recruitmentsLazyListState,
                            navigateToDetail = { recruitmentId ->
                                backStack.add(RecruitmentDetailNavKey(recruitmentId))
                            },
                        )
                        recruitmentDetailEntry(
                            popBack = backStack::removeLastOrNull,
                        )
                        favoritesEntry(
                            navigateToDetail = {
                                backStack.add(FavoriteNavKey)
                            },
                        )
                    }
                )
            }
        }
    }
}
