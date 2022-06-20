package pontinisystems.dog.data.datasources

import pontinisystems.dog.data.infra.datasources.DogDatasource
import pontinisystems.dog.data.infra.model.DogRemoteModel
import pontinisystems.network.BaseDataSource
import pontinisystems.dog.data.remote.DogApi


class DogDatasourceImpl(private val api: DogApi) : BaseDataSource(), DogDatasource {

    override suspend fun findByCategory(category: String): DogRemoteModel {
        return safeApiCall(
            apiCall = suspend {
                api.getDogsByType(category)
            },
            retry = 5
        )
    }

    override suspend fun search(text: String?, page: String, limit:String): List<DogRemoteModel> {
        return safeApiCall(
            apiCall = suspend {
                api.search(page = page , limit =limit, search = text  )
            },
            retry = 3
        )
    }
}