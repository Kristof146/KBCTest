package be.storybird.kbctest.screens.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Kristof Van Daele.
 */
@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

	private lateinit var navDelegate: NavDelegate

	fun init(navDelegate: NavDelegate) {
		this.navDelegate = navDelegate
	}

	fun navigateToGame() {
		//analytics or other shizzle can be used here before navigating
		navDelegate.navigateToGame()
	}

	interface NavDelegate {
		fun navigateToGame()
	}
}