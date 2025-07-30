package be.storybird.kbctest.screens.game

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

	private lateinit var code: String

	fun init() {
		code = repo.generateMastermindCode()
	}

	fun checkCode() {
		//TODO implement this
	}
}