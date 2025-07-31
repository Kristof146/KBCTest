package be.storybird.kbctest.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import be.storybird.kbctest.R
import be.storybird.kbctest.navigation.Screen

/**
 * Created by Kristof Van Daele.
 */

@Composable
fun HomeScreen(
	navController: NavController,
	viewModel: HomeViewModel = hiltViewModel()
) {

	LaunchedEffect(Unit) {
		viewModel.init(object : HomeViewModel.NavDelegate {
			override fun navigateToGame() {
				navController.navigate(Screen.Game.route)
			}
		})
	}

	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(16.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {

		Text(
			text = stringResource(R.string.txt_intro),
			style = MaterialTheme.typography.headlineMedium,
			textAlign = TextAlign.Center
		)

		Spacer(modifier = Modifier.height(32.dp))

		Button(
			onClick = {
				viewModel.navigateToGame()
			},
			modifier = Modifier.width(200.dp)
		) {
			Text(stringResource(R.string.btn_start))
		}
	}
}