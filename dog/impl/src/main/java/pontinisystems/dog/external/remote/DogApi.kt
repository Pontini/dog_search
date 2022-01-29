package pontinisystems.dog.external.remote


import pontinisystems.dog.infra.model.DogRemoteModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DogApi {

    @GET("/3/dog/{type}")
    suspend fun getDogsByType(@Path("type") type : String, ) : DogRemoteModel

    @GET("/v1/images/search")
    suspend fun search(@Query("search") search: String?, @Query("limit") limit: String, @Query("page") page: String): List<DogRemoteModel>

    @GET("/3/dog/{id}")
    suspend fun getDogDetails(@Path("id") id : String, @Query ("api_key") api_key: String) : DogRemoteModel
}