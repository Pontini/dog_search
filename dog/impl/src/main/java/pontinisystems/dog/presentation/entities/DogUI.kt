package pontinisystems.dog.presentation.entities

import pontinisystems.dog.domain.entities.Dog

sealed class DogUI(){

    object LoadMore : DogUI()

    class DogItem(
        val item: Dog,

    ) : DogUI()
}


