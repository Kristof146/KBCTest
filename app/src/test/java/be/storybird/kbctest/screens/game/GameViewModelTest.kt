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
		val colors = viewModel.gameState.value.boxColors
		assertEquals(listOf(GameViewModel.colorGreen, GameViewModel.colorGreen, GameViewModel.colorGreen, GameViewModel.colorGreen), colors)
	}

	@Test
	fun `updateGuess all wrong returns all red`(): Unit = runBlocking {
		viewModel.onLetterUpdated(0, "E")
		viewModel.onLetterUpdated(1, "F")
		viewModel.onLetterUpdated(2, "G")
		viewModel.onLetterUpdated(3, "H")
		viewModel.onCheckCodeClicked()
		val colors = viewModel.gameState.value.boxColors
		assertEquals(listOf(GameViewModel.colorRed, GameViewModel.colorRed, GameViewModel.colorRed, GameViewModel.colorRed), colors)
	}

	@Test
	fun `updateGuess all correct but wrong location returns all orange`(): Unit = runBlocking {
		viewModel.onLetterUpdated(0, "B")
		viewModel.onLetterUpdated(1, "C")
		viewModel.onLetterUpdated(2, "D")
		viewModel.onLetterUpdated(3, "A")
		viewModel.onCheckCodeClicked()
		val colors = viewModel.gameState.value.boxColors
		assertEquals(listOf(GameViewModel.colorOrange, GameViewModel.colorOrange, GameViewModel.colorOrange, GameViewModel.colorOrange), colors)
	}

	@Test
	fun `updateGuess mix colors`(): Unit = runBlocking {
		viewModel.onLetterUpdated(0, "A")
		viewModel.onLetterUpdated(1, "C")
		viewModel.onLetterUpdated(2, "F")
		viewModel.onLetterUpdated(3, "D")
		viewModel.onCheckCodeClicked()
		val colors = viewModel.gameState.value.boxColors
		assertEquals(listOf(GameViewModel.colorGreen, GameViewModel.colorOrange, GameViewModel.colorRed, GameViewModel.colorGreen), colors)
	}

	@Test
	fun `updateGuess duplicate letter`(): Unit = runBlocking {
		viewModel.onLetterUpdated(0, "A")
		viewModel.onLetterUpdated(1, "A")
		viewModel.onLetterUpdated(2, "F")
		viewModel.onLetterUpdated(3, "G")
		viewModel.onCheckCodeClicked()
		val colors = viewModel.gameState.value.boxColors
		assertEquals(listOf(GameViewModel.colorGreen, GameViewModel.colorRed, GameViewModel.colorRed, GameViewModel.colorRed), colors)
	}
}