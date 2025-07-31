package be.storybird.kbctest.repository

import javax.inject.Inject

/**
 * Created by Kristof Van Daele.
 */
interface MastermindRepo {

	fun generateMastermindCode(): String
}

class MastermindRepoImpl @Inject constructor() : MastermindRepo {

	//used a repo for this in case data should come from different source, for example api call
	override fun generateMastermindCode(): String {
		return (1..4)
			.map { ('A'..'Z').random() }
			.joinToString("")
	}
}