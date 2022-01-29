package pontinisystems.dog.presentation.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import pontinisystems.base_android.getOrAwaitValueTest
import pontinisystems.core.DefaultDispatcherProvider
import pontinisystems.core.Either
import pontinisystems.dog.domain.errors.Failure
import pontinisystems.dog.domain.usecases.GetDogsBySearch
import pontinisystems.dog.presentation.mapper.DogsToDogsUIMapper
import pontinisystems.dog.presentation.viewaction.DogsAction
import pontinisystems.dog.presentation.viewstate.DogsViewState
import pontinisystems.dog.util.Factory
import kotlin.test.assertEquals


@ExperimentalCoroutinesApi
class DogsViewModelTest {

    @get:Rule
    val instantTask = InstantTaskExecutorRule()

    private val useCase: GetDogsBySearch = mockk(relaxed = true)

    private val dispatcherProvider = DefaultDispatcherProvider()

    private val mapper = DogsToDogsUIMapper()

    private val viewModel by lazy {
        DogsViewModel(
            dispatcherProvider = dispatcherProvider,
            getDogsBySearch = useCase,
            dogsToDogsUIMapper = mapper
        )
    }

    @Test
    fun `return success with dog list when dispatcher search valid`() {
        val list = Factory.mockDogs()
        val expected = DogsViewState.State.Success(mapper.mapFrom(list), isUpdate)
        val page = 1
        val search = "Pan"
        val limit = 100

        coEvery {
            useCase.invoke(page = page, text = search, limit = limit)
        } returns Either.Success(list)
        viewModel.dispatchViewAction(DogsAction.Search(search))
        val result = viewModel.viewState.dogs.getOrAwaitValueTest()
        assertEquals(
            expected.dogs.isNullOrEmpty().not(), result.isNullOrEmpty().not()
        )

    }

    @Test
    fun `return InputSearch with search invalid when dispatcher search valid`() {

        val expected = DogsViewState.State.InputSearch
        val page = null
        val search = null
        val limit = null
        coEvery {
            useCase.invoke(page = page, text = search, limit = limit)
        } returns Either.Error(Failure.InputInvalid())
        viewModel.dispatchViewAction(DogsAction.Search(search))
        val result = viewModel.viewState.state.getOrAwaitValueTest()
        assertEquals(
            expected, result
        )
    }


    @Test
    fun `return Error  when dispatcher search valid`() {

        val expected = DogsViewState.State.Error("Empty Message")
        val page = null
        val search = null
        val limit = null
        coEvery {
            useCase.invoke(page = page, text = search, limit = limit)
        } returns Either.Error(Failure.NetworkFailure())
        viewModel.dispatchViewAction(DogsAction.Search(search))
        val result = viewModel.viewState.state.getOrAwaitValueTest()
        assertEquals(
            expected, result
        )
    }
}

