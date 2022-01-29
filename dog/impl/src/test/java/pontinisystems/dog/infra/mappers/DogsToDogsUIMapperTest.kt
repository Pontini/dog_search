package pontinisystems.dog.infra.mappers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import pontinisystems.dog.domain.entities.Dog
import pontinisystems.dog.presentation.entities.DogUI
import pontinisystems.dog.presentation.mapper.DogsToDogsUIMapper

@ExperimentalCoroutinesApi
class DogsToDogsUIMapperTest {

    @get:Rule
    val instantTask = InstantTaskExecutorRule()

    private val mapper = DogsToDogsUIMapper()

    @Test
    fun `return list with item when converting `() {
        val item =  Dog(
            temperament = "skillful",
            breedName = "Retriever ",
            breedFor = "imperial",
            url = "my_url",
            id = "45654456",
            lifeSpan = "12 Years",
        )

       val list = arrayListOf(item)
       val  newList = mapper.mapFrom(list)

        assert(newList.isNullOrEmpty().not())
        assert(newList[0] is DogUI.DogItem)
    }

}

