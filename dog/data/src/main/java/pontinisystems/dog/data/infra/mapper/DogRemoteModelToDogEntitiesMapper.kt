package pontinisystems.dog.data.infra.mapper

import pontinisystems.core.CustomMapper
import pontinisystems.dog.data.infra.database.entities.DogEntity
import pontinisystems.dog.data.infra.model.DogRemoteModel

class DogRemoteModelToDogEntitiesMapper : CustomMapper<List<DogRemoteModel>, List<DogEntity>> {
    override fun mapFrom(from: List<DogRemoteModel>): List<DogEntity> {
        val list = arrayListOf<DogEntity>()
        try {
            from.forEach {item->
               if(item.id.isNullOrEmpty().not() && item.breeds.isNullOrEmpty().not()){
                       list.add(
                           DogEntity(
                               id = item.id.orEmpty(),
                               url = item.url,
                               breedName = item.breeds?.get(0)?.name.orEmpty(),
                               breedFor = item.breeds?.get(0)?.bredFor.orEmpty(),
                               lifeSpan = item.breeds?.get(0)?.life_span.orEmpty(),
                               temperament = item.breeds?.get(0)?.temperament.orEmpty()
                           )
                       )
               }
            }
        } catch (e: Throwable) {
            print(e)
        }
        return list
    }


}