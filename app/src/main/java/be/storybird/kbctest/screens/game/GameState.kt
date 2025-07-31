package be.storybird.kbctest.screens.game

import androidx.compose.ui.graphics.Color

/**
 * Created by Kristof Van Daele.
 */

data class GameState(
	val guess: List<Char> = listOf(' ', ' ', ' ', ' '),
	val boxColors: List<Color> = List(4) { GameViewModel.colorDefault },
	val btnEnabled: Boolean = false
)