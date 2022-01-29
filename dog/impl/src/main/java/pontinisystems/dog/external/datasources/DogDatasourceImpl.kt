package pontinisystems.dog.external.datasources

import pontinisystems.network.BaseDataSource
import pontinisystems.dog.external.remote.DogApi
import pontinisystems.dog.infra.datasources.DogDatasource
import pontinisystems.dog.infra.model.DogRemoteModel


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