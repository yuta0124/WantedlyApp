import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.yuta0124.wantedlyapp.core.design.system.icons.WantedlyIcons
import com.yuta0124.wantedlyapp.feature.favorite.navigation.FavoriteRoute
import com.yuta0124.wantedlyapp.feature.recruitments.navigation.RecruitmentsRoute
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Composable
fun BottomNavigation(
    recruitmentsLazyListState: LazyListState,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val bottomNavItems = listOf(
        BottomNavItems.Recruitments,
        BottomNavItems.Favorite,
    )
    val currentDestination = remember {
        derivedStateOf {
            navBackStackEntry?.destination
        }
    }
    val bottomBarVisible = remember {
        derivedStateOf {
            when (currentDestination.value?.route) {
                RecruitmentsRoute::class.qualifiedName,
                FavoriteRoute::class.qualifiedName -> true

                else -> false
            }
        }
    }

    AnimatedVisibility(
        visible = bottomBarVisible.value,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        BottomAppBar(
            modifier = modifier,
            containerColor = MaterialTheme.colorScheme.background,
        ) {
            bottomNavItems.forEach { navItem ->
                val isSelected =
                    currentDestination.value?.hierarchy?.any { it.route == navItem.route::class.qualifiedName } == true
                val icon = when (navItem) {
                    BottomNavItems.Recruitments -> WantedlyIcons.List
                    BottomNavItems.Favorite -> WantedlyIcons.Favorite
                }
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                        )
                    },
                    label = { Text(navItem.title) },
                    selected = isSelected,
                    onClick = {
                        when {
                            navItem is BottomNavItems.Recruitments && isSelected -> {
                                // タブ再選択時にRecruitmentsScreenをトップまでスクロールする
                                scope.launch {
                                    recruitmentsLazyListState.animateScrollToItem(0)
                                }
                            }

                            else -> {
                                navController.navigate(navItem.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                        selectedTextColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray,
                        indicatorColor = Color.White,
                    )
                )
            }
        }
    }
}

@Serializable
sealed class BottomNavItems<T>(val title: String, val route: T) {
    data object Recruitments : BottomNavItems<RecruitmentsRoute>("recruitments", RecruitmentsRoute)
    data object Favorite : BottomNavItems<FavoriteRoute>("favorite", FavoriteRoute)
}
