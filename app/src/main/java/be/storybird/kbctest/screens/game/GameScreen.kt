package be.storybird.kbctest.screens.game

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

/**
 * Created by Kristof Van Daele.
 */

@Composable
fun GameScreen(
	navController: NavController,
	viewModel: GameViewModel = hiltViewModel()
) {

	LaunchedEffect(Unit) {
		viewModel.init()
	}

	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(16.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {

		Text(
			text = "Here comes the actual game",
			style = MaterialTheme.typography.headlineMedium,
			textAlign = TextAlign.Center
		)

		Spacer(modifier = Modifier.height(32.dp))

		Button(
			onClick = {
				//TODO do something
			},
			modifier = Modifier.width(200.dp)
		) {
			Text("Check")
		}
	}
}