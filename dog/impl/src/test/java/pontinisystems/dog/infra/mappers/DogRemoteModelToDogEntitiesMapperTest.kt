package pontinisystems.dog.infra.mappers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import pontinisystems.dog.infra.mapper.DogRemoteModelToDogEntitiesMapper
import pontinisystems.dog.infra.model.BreedRemoteModel
import pontinisystems.dog.infra.model.DogRemoteModel

@ExperimentalCoroutinesApi
class DogRemoteModelToDogEntitiesMapperTest {

    @get:Rule
    val instantTask = InstantTaskExecutorRule()

    private val mapper = DogRemoteModelToDogEntitiesMapper()

    @Test
    fun `return list with item when converting `() {
        val item = DogRemoteModel(
            breeds = listOf(
                BreedRemoteModel(
                    temperament = "skillful",
                    bredFor = "imperial",
                    name = "Retrive",
                    id = 190,
                )
            ),
            id = "125"
        )
        val list = arrayListOf(item)

        val newList = mapper.mapFrom(list)

        assert(newList.isNullOrEmpty().not())
        assert(newList[0].id == item.id)

    }


    @Test
    fun `return list without item when converting with id null `() {
        val item = DogRemoteModel(
            breeds = listOf(
                BreedRemoteModel(
                    temperament = "skillful",
                    bredFor = "imperial",
                    name = "Retrive",
                    id = 190,
                )
            ),
            id = null
        )
        val list = arrayListOf(item)

        val newList = mapper.mapFrom(list)

        assert(newList.isNullOrEmpty())

    }

}

