package be.storybird.kbctest.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import be.storybird.kbctest.screens.game.GameScreen
import be.storybird.kbctest.screens.home.HomeScreen

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
			GameScreen()
		}
	}
}

sealed class Screen(val route: String) {
	data object Home : Screen("home")
	data object Game : Screen("game")
}