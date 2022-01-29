plugins {
    id("java-library")
    id( "kotlin")
    id("kotlin-kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation ("org.jetbrains.kotlin:kotlin-stdlib:${Lib.Kotlin.version}")

    implementation(Lib.Retrofit.retrofit2)
    implementation(Lib.Gson.scalarsGson)
    implementation(Lib.Gson.converterGson)
    implementation(Lib.Kotlin.coroutines)
    implementation(Lib.Kotlin.coroutinesAdapter)
    implementation(Lib.Okhttp.okhttp)
    implementation(Lib.Okhttp.okhttpInterceptor)
    implementation(Lib.Koin.koinCore)
    testImplementation(Lib.Koin.koinTest)





}