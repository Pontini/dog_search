package pontinisystems.dog.presentation.viewaction

import pontinisystems.dog.domain.entities.Dog

sealed class DogsAction {
    object Init : DogsAction()
    data class OpenDetails(val item: Dog) : DogsAction()
    data class Search(val search: String?) : DogsAction()
    object LoadMore : DogsAction()
    object ChangeVisualization : DogsAction()
}