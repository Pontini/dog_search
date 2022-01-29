package pontinisystems.dog.presentation.viewmodel

import pontinisystems.base_android.ActionDispatcher
import pontinisystems.base_android.BaseViewModel
import pontinisystems.dog.domain.entities.Dog
import pontinisystems.dog.presentation.viewaction.DogDetailsAction
import pontinisystems.dog.presentation.viewstate.DogDetailsViewState


class DogDetailsViewModel(
) : BaseViewModel<DogDetailsViewState, DogDetailsAction>(),
    ActionDispatcher<DogDetailsAction> {

    override val viewState: DogDetailsViewState = DogDetailsViewState()

    override fun dispatchViewAction(viewAction: DogDetailsAction) {
        when (viewAction) {
            is DogDetailsAction.Init -> {
                try {
                    viewState.state.value = DogDetailsViewState.State.Loading
                    val temp = viewAction.data
                    viewState.dog.value = temp as Dog
                    viewState.state.value = DogDetailsViewState.State.Success(temp)
                } catch (e: Throwable) {
                    handlerUnknown()
                }

            }
        }
    }
    private fun handlerUnknown() {
        viewState.state.postValue(DogDetailsViewState.State.Error( message =  "failed to display details"))
    }

}