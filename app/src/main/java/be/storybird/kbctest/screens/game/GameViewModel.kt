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

	private companion object{
		private val colorGreen = Color.Green
		private val colorRed = Color.Red
		private val colorOrange = Color(0xFFFF9800)
		private val colorDefault = Color(0xFFE0E0E0)
	}

	private lateinit var code: String
	val _boxColors = MutableLiveData<List<Color>>()
	val _btnEnabled = MutableLiveData<Boolean>()
	val guess: List<Char> = listOf(' ', ' ', ' ', ' ')

	val boxColors: LiveData<List<Color>> get() = _boxColors
	val btnEnabled: LiveData<Boolean> get() = _btnEnabled

	fun init() {
		code = repo.generateMastermindCode()
		_btnEnabled.value = false
		_boxColors.value = listOf(colorDefault, colorDefault, colorDefault, colorDefault)
	}

	fun onLetterUpdated(position: Int, letter: String) {

	}

	fun onCheckCodeClicked() {
		//TODO implement this
	}
}