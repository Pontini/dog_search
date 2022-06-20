package pontinisystems.dog.presentation.mapper

import pontinisystems.core.CustomMapper
import pontinisystems.dog.domain.entities.Dog
import pontinisystems.dog.presentation.entities.DogUI

class DogsToDogsUIMapper : CustomMapper<List<Dog>, ArrayList<DogUI>> {
    override fun mapFrom(from: List<Dog>): ArrayList<DogUI> {
        val list = arrayListOf<DogUI>()
        from.forEach {
            list.add(DogUI.DogItem(item = it))
        }
        return list
    }


}