package be.storybird.kbctest.repository

import javax.inject.Inject

/**
 * Created by Kristof Van Daele.
 */
interface MastermindRepo {

	fun getMastermindCode(): String
}

class MastermindRepoImpl @Inject constructor() : MastermindRepo {

	override fun getMastermindCode(): String {
		return "ABCD"
	}
}