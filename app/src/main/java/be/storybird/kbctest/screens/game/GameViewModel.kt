package be.storybird.kbctest.screens.game

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.storybird.kbctest.repository.MastermindRepo
import dagger.hilt.android.lifecycle.HiltViewModel
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
	val _gameState = MutableLiveData<GameState>()
	val gameState: LiveData<GameState> get() = _gameState

	fun init() {
		code = repo.generateMastermindCode()
	}

	fun onLetterUpdated(position: Int, letter: String) {
		//TODO implement this
	}

	fun onCheckCodeClicked() {
		//TODO implement this
	}
}