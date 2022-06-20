package pontinisystems.dog.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pontinisystems.base_android.ActionDispatcher
import pontinisystems.base_android.BaseViewModel
import pontinisystems.core.DispactcherProvider
import pontinisystems.dog.presentation.viewaction.DogsAction
import pontinisystems.core.Either
import pontinisystems.dog.domain.errors.Failure
import pontinisystems.dog.domain.usecases.GetDogsBySearch
import pontinisystems.dog.presentation.mapper.DogsToDogsUIMapper
import pontinisystems.dog.presentation.viewstate.DogsViewState
import pontinisystems.dog.presentation.viewstate.DogsViewState.Companion.DEFAULT_LIMIT
import pontinisystems.dog.presentation.viewstate.DogsViewState.Companion.DEFAULT_PAGE


class DogsViewModel(
    private val dispatcherProvider: DispactcherProvider,
    private val getDogsBySearch: GetDogsBySearch,
    private val dogsToDogsUIMapper: DogsToDogsUIMapper,
) : BaseViewModel<DogsViewState, DogsAction>(),
    ActionDispatcher<DogsAction> {


    override val viewState: DogsViewState = DogsViewState()

    override fun dispatchViewAction(viewAction: DogsAction) {
        when (viewAction) {
            is DogsAction.Init -> {
                if (viewState.dogs.value.isNullOrEmpty()) {
                    search("")
                }
            }
            is DogsAction.Search -> {
                search(viewAction.search)
            }

            is DogsAction.OpenDetails -> {
                viewState.action.value = DogsViewState.Action.OpenDetails(viewAction.item)
            }
            DogsAction.LoadMore -> {
                loadMore()
            }
            DogsAction.ChangeVisualization -> {
                if (viewState.visualization == DogsViewState.Visualization.GRID) {
                    viewState.visualization = DogsViewState.Visualization.LIST
                } else {
                    viewState.visualization = DogsViewState.Visualization.GRID
                }
                viewState.action.postValue(
                    DogsViewState.Action.ChangeVisualization(viewState.visualization)
                )
            }
        }
    }

    private fun search(search: String?) {
        viewState.textSearch = search
        viewState.page = DEFAULT_PAGE
        viewState.limit = DEFAULT_LIMIT
        viewState.dogs.value?.clear()
        viewState.state.value = DogsViewState.State.Loading
        fetchDogs(false)
    }

    private fun loadMore(){
        incrementPage()
        fetchDogs(true)
    }

    private fun incrementPage(value: Int = 1) {
        viewState.page += value
    }

    private fun fetchDogs(isUpdate:Boolean) {
        viewModelScope.launch(dispatcherProvider.io()) {
            when (val result =
                getDogsBySearch(viewState.textSearch, viewState.page, viewState.limit)) {
                is Either.Success -> {
                    val dogsUI = dogsToDogsUIMapper.mapFrom(result.data)
                    viewState.dogs.postValue(dogsUI)
                    viewState.state.postValue(DogsViewState.State.Success(dogsUI, isUpdate))
                }
                is Either.Error -> {
                    when (result.error) {
                        is Failure.NetworkFailure -> handlerUnknown(result.error.message)
                        is Failure.InputInvalid -> {
                            clearPage()
                            viewState.state.postValue(DogsViewState.State.InputSearch)
                        }
                        is Failure.Unknown -> handlerUnknown(result.error.message)
                    }
                }
            }
        }
    }

    private fun clearPage() {
        viewState.page = DEFAULT_PAGE
    }

    private fun handlerUnknown(message: String?) {
        message?.let {
            viewState.state.postValue(DogsViewState.State.Error(message))
        } ?: run {
            viewState.state.postValue(DogsViewState.State.Error("Exception from Exception "))
        }
    }
}