package be.storybird.kbctest.screens.game

import be.storybird.kbctest.repository.MastermindRepo
import be.storybird.kbctest.repository.MastermindRepoImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Created by Kristof Van Daele.
 */
class GameViewModelTest {

	private lateinit var viewModel: GameViewModel
	private val repo: MastermindRepo = object : MastermindRepo{
		override fun generateMastermindCode(): String {
			return "ABCD"
		}
	}

	@Before
	fun setup() {
		viewModel = GameViewModel(repo)
		viewModel.init()
	}

	@Test
	fun `updateGuess updates the guess in state`(): Unit = runBlocking {
		viewModel.onLetterUpdated(0, "B")
		viewModel.onLetterUpdated(1, "C")
		viewModel.onLetterUpdated(2, "D")
		viewModel.onLetterUpdated(3, "E")
		val guess = viewModel.gameState.value.guess
		assertEquals(listOf('B', 'C', 'D', 'E'), guess)
	}

	@Test
	fun `updateGuess correct returns all green`(): Unit = runBlocking {
		viewModel.onLetterUpdated(0, "A")
		viewModel.onLetterUpdated(1, "B")
		viewModel.onLetterUpdated(2, "C")
		viewModel.onLetterUpdated(3, "D")
		viewModel.onCheckCodeClicked()
		val guess = viewModel.gameState.value.guess
		val colors = viewModel.gameState.value.boxColors
		assertEquals(listOf('A', 'B', 'C', 'D'), guess)
		assertEquals(listOf(GameViewModel.colorGreen, GameViewModel.colorGreen, GameViewModel.colorGreen, GameViewModel.colorGreen), colors)
	}
}