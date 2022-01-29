/*
package pontinisystems.network

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit


class Note {

}

interface NoteService {
    @GET("notes")
    fun list():Call<List<Note>>
}


class RetrofitInitializer {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.0.23:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun noteService(): NoteService = retrofit.create(NoteService::class.java)

}

val okHttpClient: OkHttpClient = OkHttpClient.Builder()
    .readTimeout(60, TimeUnit.SECONDS)
    .connectTimeout(60, TimeUnit.SECONDS)
    .build()

fun main() {
    val call = RetrofitInitializer().noteService().list()
    call.enqueue(object: Callback<List<Note>?> {
        override fun onResponse(call: Call<List<Note>?>?,
                                response: Response<List<Note>?>?) {


            response?.body()?.let {
                val notes: List<Note> = it
            }
        }

        override fun onFailure(call: Call<List<Note>?>?,
                               t: Throwable?) {

        }
    })

    CoroutineScope(Dispatchers.IO).launch {
      */
/*  val resposta = service.buscaEndereco(cep).execute()
        liveData.postValue(resposta.body())*//*

    }

}


*/
