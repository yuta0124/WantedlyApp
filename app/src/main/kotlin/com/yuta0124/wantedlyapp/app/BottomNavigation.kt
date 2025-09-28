import androidx.annotation.StringRes
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import com.yuta0124.wantedlyapp.core.design.system.R
import com.yuta0124.wantedlyapp.core.design.system.icons.WantedlyIcons
import com.yuta0124.wantedlyapp.feature.favorite.navigation.FavoriteNavKey
import com.yuta0124.wantedlyapp.feature.recruitments.navigation.RecruitmentsNavKey
import kotlinx.coroutines.launch

@Composable
fun BottomNavigation(
    recruitmentsLazyListState: LazyListState,
    currentDestination: NavKey?,
    navigateTo: (NavKey) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()
    val bottomNavItems = listOf(
        BottomNavItems.Recruitments,
        BottomNavItems.Favorite,
    )

    val bottomBarVisible by remember(currentDestination) {
        mutableStateOf(
            when (currentDestination) {
                RecruitmentsNavKey,
                FavoriteNavKey -> true

                else -> false
            }
        )
    }

    AnimatedVisibility(
        visible = bottomBarVisible,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        BottomAppBar(
            modifier = modifier,
            containerColor = MaterialTheme.colorScheme.background,
        ) {
            bottomNavItems.forEach { navItem ->
                val isSelected = currentDestination == navItem.route

                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = navItem.icon,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                        )
                    },
                    label = { Text(stringResource(navItem.titleRes)) },
                    selected = isSelected,
                    onClick = {
                        when {
                            navItem is BottomNavItems.Recruitments && isSelected -> {
                                // タブ再選択時にRecruitmentsScreenをトップまでスクロールする
                                scope.launch {
                                    recruitmentsLazyListState.animateScrollToItem(0)
                                }
                            }

                            else -> navigateTo(navItem.route)
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

sealed class BottomNavItems<T>(
    @StringRes val titleRes: Int,
    val icon: ImageVector,
    val route: T,
) {
    data object Recruitments : BottomNavItems<RecruitmentsNavKey>(
        titleRes = R.string.recruitments,
        icon = WantedlyIcons.List,
        route = RecruitmentsNavKey,
    )

    data object Favorite : BottomNavItems<FavoriteNavKey>(
        titleRes = R.string.favorite,
        WantedlyIcons.Favorite,
        FavoriteNavKey,
    )
}
