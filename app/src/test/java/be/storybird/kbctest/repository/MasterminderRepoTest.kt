package be.storybird.kbctest.repository

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Created by Kristof Van Daele.
 */
class MasterminderRepoTest {
	private lateinit var repo: MastermindRepo

	@Before
	fun setup() {
		repo = MastermindRepoImpl()
	}

	@Test
	fun `generate code produces 4 uppercase letters`(): Unit = runBlocking {
		val answer = repo.generateMastermindCode()
		assertEquals(4, answer.length)
		assertTrue(answer.all { it in 'A'..'Z' })
	}
}