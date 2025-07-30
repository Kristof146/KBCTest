package be.storybird.kbctest.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import be.storybird.kbctest.home.HomeScreen

/**
 * Created by Kristof Van Daele.
 */
@Composable
fun NavGraph(
	navController: NavHostController,
	modifier: Modifier = Modifier
) {
	NavHost(
		navController = navController,
		startDestination = Screen.Home.route,
		modifier = modifier
	) {
		composable(route = Screen.Home.route) {
			HomeScreen(navController = navController)
		}
		composable(route = Screen.Game.route) {
			//TODO
		}
		// Add more screens here as needed
	}
}

sealed class Screen(val route: String) {
	object Home : Screen("home")
	object Game : Screen("game")
	// Add more screens here as needed
}