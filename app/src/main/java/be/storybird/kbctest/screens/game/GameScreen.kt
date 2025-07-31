package be.storybird.kbctest.screens.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import be.storybird.kbctest.R

/**
 * Created by Kristof Van Daele.
 */

@Composable
fun GameScreen(
	viewModel: GameViewModel = hiltViewModel()
) {

	val gameState by viewModel.gameState.collectAsState()

	LaunchedEffect(Unit) {
		viewModel.init()
	}

	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(8.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		Row(
			horizontalArrangement = Arrangement.spacedBy(16.dp)
		) {
			for (i in 0..3) {
				val value = gameState.guess.getOrNull(i)?.toString()?.takeIf { it != " " } ?: ""
				OutlinedTextField(
					value = value,
					onValueChange = { newValue ->
						val char = newValue.uppercase().take(1).getOrNull(0)
						if (char != null && char in 'A'..'Z') {
							viewModel.onLetterUpdated(i, char.toString())
						} else if (newValue.isEmpty()) {
							viewModel.onLetterUpdated(i, " ")
						} else {
							throw Exception("this should not happen")
							//it should never arrive in else case
						}
					},
					modifier = Modifier
						.width(56.dp)
						.height(56.dp)
						.background(
							color = gameState.boxColors.get(i),
							shape = RoundedCornerShape(8.dp)
						),
					singleLine = true,
					textStyle = LocalTextStyle.current.copy(
						textAlign = TextAlign.Center,
						color = Color.Black,
						fontSize = MaterialTheme.typography.headlineSmall.fontSize
					),
					keyboardOptions = KeyboardOptions(
						capitalization = KeyboardCapitalization.Characters,
						imeAction = ImeAction.Next
					),
					visualTransformation = VisualTransformation.None,
					maxLines = 1
				)
			}
		}

		Spacer(modifier = Modifier.height(32.dp))

		Button(
			onClick = { viewModel.onCheckCodeClicked() },
			enabled = gameState.btnEnabled
		) {
			Text(stringResource(R.string.btn_check))
		}
	}
}