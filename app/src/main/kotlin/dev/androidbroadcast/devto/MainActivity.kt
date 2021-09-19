package dev.androidbroadcast.devto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint
import dev.androidbroadcast.devto.home.HomeScreen
import dev.androidbroadcast.devto.theme.DevToTheme
import dev.androidbroadcast.devto.user.CurrentUser

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DevToTheme() {
                ProvideWindowInsets {
                    val navController = rememberNavController()
                    Scaffold(
                        backgroundColor = MaterialTheme.colors.background,
                        bottomBar = {
                            DevtoBottomNavigation(
                                navController = navController,
                                items = listOf(Screen.Home, Screen.MyProfile)
                            )
                        }
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = Screen.Home.route
                        ) {
                            composable(Screen.Home.route) { HomeScreen() }
                            composable(Screen.MyProfile.route) { CurrentUser() }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DevtoBottomNavigation(
    navController: NavController,
    items: List<Screen>,
    modifier: Modifier = Modifier
) {
    BottomNavigation(modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(screen.icon, contentDescription = null) },
                label = { Text(stringResource(screen.textResId)) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

sealed class Screen(
    val route: String,
    @StringRes val textResId: Int,
    val icon: ImageVector
) {
    object Home : Screen(ROUTE_HOME, R.string.nav_home, Icons.Filled.Home)

    object MyProfile : Screen(ROUTE_MY_PROFILE, R.string.nav_my_profile, Icons.Filled.Person)

    companion object {

        const val ROUTE_HOME = "home"
        const val ROUTE_MY_PROFILE = "my_profile"
    }
}
