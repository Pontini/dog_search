package pontinisystems.dog.presentation.viewstate

import androidx.lifecycle.MutableLiveData
import pontinisystems.dog.domain.entities.Dog


class DogDetailsViewState {
    val state = MutableLiveData<State>()

    val action = MutableLiveData<Action>()

    val dog = MutableLiveData<Dog>()

    sealed class Action {
        data class LikeOrDislike(val like:Boolean) : Action()
    }

    sealed class State {
        data class Success(val dog: Dog) : State()
        object Loading : State()
        data class Error(val message: String) : State()
    }
}

