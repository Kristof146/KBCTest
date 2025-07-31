package be.storybird.kbctest.screens.game

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import be.storybird.kbctest.repository.MastermindRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * Created by Kristof Van Daele.
 */
@HiltViewModel
class GameViewModel @Inject constructor(
	private val repo: MastermindRepo
) : ViewModel() {

	companion object {
		private val colorGreen = Color.Green
		private val colorRed = Color.Red
		private val colorOrange = Color(0xFFFF9800)
		val colorDefault = Color(0xFFE0E0E0)
	}

	private lateinit var code: String
	
	private val _gameState = MutableStateFlow(GameState())
	val gameState: StateFlow<GameState> = _gameState.asStateFlow()

	fun init() {
		code = repo.generateMastermindCode()
	}

	fun onLetterUpdated(position: Int, letter: String) {
		_gameState.update { currentState ->
			val newGuess = currentState.guess.toMutableList()
			newGuess[position] = letter.first()
			
			val isButtonEnabled = newGuess.all { it != ' ' }
			
			currentState.copy(
				guess = newGuess,
				btnEnabled = isButtonEnabled
			)
		}
	}

	fun onCheckCodeClicked() {
		val currentGuess = _gameState.value.guess.joinToString("")
		val newBoxColors = mutableListOf<Color>()
		
		for (i in 0..3) {
			val guessChar = currentGuess[i]
			val codeChar = code[i]
			
			val color = when {
				guessChar == codeChar -> colorGreen
				code.contains(guessChar) -> colorOrange
				else -> colorRed
			}
			newBoxColors.add(color)
		}
		
		_gameState.update { currentState ->
			currentState.copy(
				boxColors = newBoxColors,
				btnEnabled = false // Disable button after checking
			)
		}
	}
}