package be.storybird.kbctest.home

import androidx.lifecycle.ViewModel
import be.storybird.kbctest.repository.MastermindRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Kristof Van Daele.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
	private val repo: MastermindRepo
) : ViewModel() {

	private lateinit var navDelegate: NavDelegate

	fun init(navDelegate: NavDelegate) {
		this.navDelegate = navDelegate
	}

	fun navigateToGame() {
		//analytics or other shizzle
		navDelegate.navigateToGame()
	}

	interface NavDelegate {
		fun navigateToGame()
	}
}