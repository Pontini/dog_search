package pontinisystems.dog.presentation.viewstate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import pontinisystems.dog.domain.entities.Dog
import pontinisystems.dog.presentation.entities.DogUI


class DogsViewState {
    companion object{
        const val DEFAULT_PAGE = 1
        const val DEFAULT_LIMIT = 12
    }

    val state = MutableLiveData<State>()

    val action = MutableLiveData<Action>()

    var page = DEFAULT_PAGE

    var limit = DEFAULT_LIMIT

    var visualization:Visualization = Visualization.GRID

    val dogs = MutableLiveData<ArrayList<DogUI>>()

    var textSearch:String? =""

    sealed class Action {
        data class OpenDetails(val data:Dog) : Action()
        data class ChangeVisualization(val visualization: Visualization):Action()
    }

    val isLoading: LiveData<Boolean> = Transformations.map(state) {
        it is State.Loading
    }

    val isInputSearch: LiveData<Boolean> = Transformations.map(state) {
        it is State.InputSearch
    }

    val isError: LiveData<Boolean> = Transformations.map(state) {
        it is State.Error
    }

    val isSuccess: LiveData<Boolean> = Transformations.map(state) {
        it is State.Success && dogs.value.isNullOrEmpty().not()
    }

    val isEmptyResult: LiveData<Boolean> = Transformations.map(state) {
        it is State.Success && dogs.value.isNullOrEmpty()
    }

    sealed class State {
        data class Success(val dogs: List<DogUI>, val isUpdate: Boolean, ) : State()
        object Loading : State()
        object InputSearch : State()
        data class Error(val message: String) : State()
    }
    
    sealed class Visualization{
        object GRID:Visualization()
        object LIST:Visualization()
    }
}


