package com.example.gitrepos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.gitrepos.data.DataRepository
import com.example.gitrepos.network.GitRepoService
import com.example.gitrepos.network.model.ErrorResponse
import com.example.gitrepos.network.model.GitRepo
import com.example.gitrepos.util.Constants
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import retrofit2.Response

class StatusRepositoryTest {

    @Rule
    @JvmField
    var mockitoRule = MockitoJUnit.rule()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var gitRepoService: GitRepoService

    @Mock
    lateinit var response: Response<List<GitRepo>?>

    lateinit var dataRepository: DataRepository

    @Mock
    lateinit var success: (List<GitRepo>?) -> (Unit)

    @Mock
    lateinit var error: (ErrorResponse) -> (Unit)

    @Before
    fun setUp() {
        //Given
        dataRepository = DataRepository(gitRepoService)
    }

    @Test
    fun testDataRepoSuccess() = runBlocking {

        whenever(response.body()).thenAnswer { TestUtility.getTestGitRepoData() }
        whenever(response.isSuccessful).thenAnswer { true }
        whenever(gitRepoService.getGitRepo()).thenAnswer {
            response
        }

        //When
        dataRepository.getGitRepo(success, error)

        //Then
        verify(success)(any())
    }


    @Test
    fun testDataRepoFailure() = runBlocking {
        whenever(response.body()).thenAnswer { TestUtility.getTestGitRepoData() }
        whenever(response.isSuccessful).thenAnswer { false }
        whenever(gitRepoService.getGitRepo()).thenAnswer {
            response
        }

        //When
        dataRepository.getGitRepo(success, error)

        //Then
        verify(error)(any())
    }
}
