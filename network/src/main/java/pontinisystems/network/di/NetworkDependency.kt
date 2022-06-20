package pontinisystems.network.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import retrofit2.converter.scalars.ScalarsConverterFactory


val networkModule = module {

    factory { provideGson() }
    factory { provideOkHttpClient() }
    factory { provideRetrofit(get(),get()) }

}


fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
    .readTimeout(60, TimeUnit.SECONDS)
    .connectTimeout(60, TimeUnit.SECONDS)
    .addInterceptor(provideHttpLoggingInterceptor())
    .build()

fun provideHttpLoggingInterceptor():HttpLoggingInterceptor{
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level=HttpLoggingInterceptor.Level.BODY
    return httpLoggingInterceptor
}

fun provideGson(): Gson {
    return GsonBuilder()
        .setLenient()
        .create()
}


fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl("https://api.thedogapi.com")
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(GsonConverterFactory.create(gson))
    .client(okHttpClient)
    .build()