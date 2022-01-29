package pontinisystems.dog.domain.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import pontinisystems.core.Either
import pontinisystems.dog.domain.errors.Failure
import pontinisystems.dog.domain.repositories.DogRepository
import pontinisystems.dog.util.Factory
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class GetDogsBySearchTest {

    @get:Rule
    val instantTask = InstantTaskExecutorRule()

    private val repository: DogRepository = mockk(relaxed = true)

    private val useCase: GetDogsBySearch by lazy {
        GetDogsBySearchImpl(repository)
    }

    @Test
    fun `return success when search dogs`() = runBlockingTest {
        val page = "1"
        val search = "Pan"
        val limit = "50"

        val expected = Either.Success(Factory.mockDogs())

        coEvery {
            repository.search(text = search, page = page, limit = limit)
        } returns Either.Success(Factory.mockDogs())

        val result = useCase.invoke(text = search, page = page.toInt(), limit = limit.toInt())
        assertTrue(result.isLeft)
        assertEquals(expected, result)
    }

    @Test
    fun `return failure exception when get dogs`() = runBlockingTest {
        val page = "1"
        val search = "Pan"
        val limit = "50"

        val expected = Either.Error(Failure.Unknown())

        coEvery {
            repository.search(text = search, page = page, limit = limit)
        } returns expected

        val result = useCase.invoke(text = search, page = page.toInt(), limit = limit.toInt())
        assertTrue(result.isRight)
        assertEquals(expected, result)
    }
}