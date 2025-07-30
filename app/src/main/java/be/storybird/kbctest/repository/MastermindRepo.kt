package be.storybird.kbctest.repository

import javax.inject.Inject

/**
 * Created by Kristof Van Daele.
 */
interface MastermindRepo {

	fun generateMastermindCode(): String
}

class MastermindRepoImpl @Inject constructor() : MastermindRepo {

	override fun generateMastermindCode(): String {
		return (1..4)
			.map { ('A'..'Z').random() }
			.joinToString("")
	}
}