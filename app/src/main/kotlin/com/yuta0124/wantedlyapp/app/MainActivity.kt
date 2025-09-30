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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
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
            val currentDestination = backStack.lastOrNull()
            val bottomBarVisible by remember(currentDestination) {
                mutableStateOf(
                    when (currentDestination) {
                        RecruitmentsNavKey,
                        FavoriteNavKey -> true

                        else -> false
                    }
                )
            }
            Scaffold(
                bottomBar = {
                    BottomNavigation(
                        isVisible = bottomBarVisible,
                        recruitmentsLazyListState = recruitmentsLazyListState,
                        currentDestination = currentDestination,
                        navigateTo = backStack::add,
                    )
                }
            ) { innerPadding ->
                val padding = if (bottomBarVisible) {
                    innerPadding.calculateBottomPadding()
                } else {
                    Dp.Hairline
                }

                NavDisplay(
                    modifier = Modifier.padding(bottom = padding),
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
