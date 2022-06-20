package pontinisystems.dog.presentation.viewaction

import java.io.Serializable

sealed class DogDetailsAction {
    data class Init(val data: Serializable?) : DogDetailsAction()
}