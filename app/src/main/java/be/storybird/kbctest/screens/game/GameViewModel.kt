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
		val colorGreen = Color.Green
		val colorRed = Color.Red
		val colorOrange = Color(0xFFFF9800)
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
				boxColors = List(4) { colorDefault },
				btnEnabled = isButtonEnabled
			)
		}
	}

	fun onCheckCodeClicked() {
		val newBoxColors = MutableList(4) { colorRed }

		val answerChars = code.toCharArray()
		val guessChars = _gameState.value.guess.joinToString("").toCharArray().map { it.uppercaseChar() }.toCharArray()
		val answerUsed = BooleanArray(4)
		val guessUsed = BooleanArray(4)

		//check all greens first
		for (i in 0..3) {
			if (guessChars[i] == answerChars[i]) {
				newBoxColors[i] = colorGreen
				answerUsed[i] = true
				guessUsed[i] = true
			}
		}

		//check orange next
		for (i in 0..3) {
			if (!guessUsed[i]) {
				for (j in 0..3) {
					if (!answerUsed[j] && guessChars[i] == answerChars[j]) {
						newBoxColors[i] = colorOrange
						answerUsed[j] = true
						break
					}
				}
			}
		}

		//those unchanged are red
		
		_gameState.update { currentState ->
			currentState.copy(
				boxColors = newBoxColors,
				btnEnabled = false // Disable button after checking
			)
		}
	}
}