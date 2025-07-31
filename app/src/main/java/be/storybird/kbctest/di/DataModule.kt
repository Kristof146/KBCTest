package be.storybird.kbctest.di

import be.storybird.kbctest.repository.MastermindRepo
import be.storybird.kbctest.repository.MastermindRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Kristof Van Daele.
 */

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {

	@Binds
	@Singleton
	abstract fun bindMasterMindRepository(repo: MastermindRepoImpl): MastermindRepo
}