package pontinisystems.dog.data.infra.datasources

import pontinisystems.dog.data.infra.model.DogRemoteModel

interface DogDatasource {
    suspend fun findByCategory(category: String): DogRemoteModel
    suspend fun search(text: String?, page: String, limit: String): List<DogRemoteModel>
}